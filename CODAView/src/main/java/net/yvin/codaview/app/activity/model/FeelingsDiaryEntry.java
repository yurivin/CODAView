package net.yvin.codaview.app.activity.model;

import android.util.Log;

import java.util.StringTokenizer;

/**
 * Created by Yuriy.Vinogradov on 13.08.2014.
 */
public class FeelingsDiaryEntry{

    public FeelingsDiaryEntry(String yearFrom, String monthFrom, String dayFrom, String hourFrom, String minuteFrom,
                             String yearTo, String monthTo, String dayTo, String hourTo, String minuteTo, String feelingsIntensity,
                             String feelingRating, String selectedFeelings, String comment) {
        this.yearFrom = yearFrom;
        this.monthFrom = monthFrom;
        this.dayFrom = dayFrom;
        this.hourFrom = hourFrom;
        this.minuteFrom = minuteFrom;
        this.yearTo = yearTo;
        this.monthTo = monthTo;
        this.dayTo = dayTo;
        this.hourTo = hourTo;
        this.minuteTo = minuteTo;
        this.feelingsIntensity = feelingsIntensity;
        this.feelingRating = feelingRating;
        this.selectedFeelings = getFeelings(selectedFeelings);
        this.comment = comment;
    }

    public final String yearFrom;
    public final String monthFrom;
    public final String dayFrom;
    public final String hourFrom;
    public final String minuteFrom;
    public final String yearTo;
    public final String monthTo;
    public final String dayTo;
    public final String hourTo;
    public final String minuteTo;
    public final String feelingsIntensity;
    public final String feelingRating;
    public final String[] selectedFeelings;
    public final String comment;

    private String[] getFeelings(String feelingsdata) {
        String feelings =  feelingsdata.replace("[", "");
        feelings = feelings.replace("]", "");
        StringTokenizer tokenizer = new StringTokenizer(feelings, ", ");
        String[] feelingsArray = new String[tokenizer.countTokens()];
        for (int i = 0; i < feelingsArray.length; i++) {
            feelingsArray[i] = (String) tokenizer.nextElement();
        }
        StringBuilder builder = new StringBuilder();
        for(String part : feelingsArray) {
            builder.append(part);
            builder.append(",");
        }
        Log.d("Feelings: ", builder.toString());
        return feelingsArray;
    }
}
