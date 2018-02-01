package behappy.danielstaranowicz.com.behappy.data;

import java.util.ArrayList;

import behappy.danielstaranowicz.com.behappy.model.Quote;

/**
 * Created by danie on 16.01.2018.
 */

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quotes);
}
