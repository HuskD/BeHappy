package behappy.danielstaranowicz.com.behappy;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);




        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView byAuthor = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardview);

        String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");

        int colors[] = new int[] {R.color.blue_500, R.color.pink_700, R.color.green_400,
        R.color.lime_400, R.color.orange_400, R.color.amber_800, R.color.pink_800,
        R.color.grey_700, R.color.amber_400, R.color.blue_900, R.color.brown_800, R.color.cyan_400, R.color.red_500,
        R.color.deep_orange_600, R.color.deep_purple_A200, R.color.orange_500, R.color.grey_800, R.color.teal_800, R.color.teal_400, R.color.blue_A400,
                R.color.blue_300, R.color.purple_700, R.color.purple_200, R.color.brown_200,
                R.color.light_green_A700, R.color.indigo_900, R.color.pink_300, R.color.brown_700};

        quoteText.setText(quote);
        byAuthor.setText("-" + author);



        cardView.setBackgroundResource(getRandomQuote(colors));

        return quoteView;
    }



    public static final QuoteFragment newInstance(String quote, String author) {
        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);


        return fragment;
    }

    int getRandomQuote(int[] collorArray) {
        int color;
        int quotesArrayLength = collorArray.length;

        int randomNum = ThreadLocalRandom.current().nextInt(quotesArrayLength);

        color = collorArray[randomNum];

        return color;
    }



}
