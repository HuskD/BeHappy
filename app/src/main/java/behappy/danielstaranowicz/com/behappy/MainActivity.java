package behappy.danielstaranowicz.com.behappy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import behappy.danielstaranowicz.com.behappy.data.QuoteData;
import behappy.danielstaranowicz.com.behappy.data.QuoteListAsyncResponse;
import behappy.danielstaranowicz.com.behappy.data.QuoteViewPagerAdapter;
import behappy.danielstaranowicz.com.behappy.model.Quote;

public class MainActivity extends AppCompatActivity {
    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;
    private Button mShareButton;
    private String curQuote;
    private String curAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);





        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);
        Toast.makeText(MainActivity.this, "Do poprawnego działania aplikacji wymagane jest połączenie z internetem.", Toast.LENGTH_SHORT).show();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Here's your instance
                QuoteFragment fragment = (QuoteFragment) quoteViewPagerAdapter.getRegisteredFragment(position);
                curQuote = fragment.getArguments().getString("quote");
                curAuthor = fragment.getArguments().getString("author");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mShareButton = (Button) findViewById(R.id.shareButton);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(curAuthor != null && curQuote != null) {
                    String shareBody = "\"" + curQuote + "\"" + " - " + curAuthor + "\n Więcej cytatów w aplikacji Inspiracje: https://goo.gl/ru9JsK";
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Temat");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Udostępnij znajomym: "));
                } else {
                    Toast.makeText(MainActivity.this, "Udostępnienie w tej chwili niemożliwe.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quotes.get(i).getQuote(),
                            quotes.get(i).getAuthor()
                    );

                    fragmentList.add(quoteFragment);
                    //Log.i("Name",quotes.get(i).getAuthor() );
                    Collections.shuffle(fragmentList);
                }
                quoteViewPagerAdapter.notifyDataSetChanged();

            }
        });

        return fragmentList;
    }





}
