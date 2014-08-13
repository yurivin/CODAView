package net.yvin.codaview.app.activity.model;

import net.yvin.codaview.app.utils.FeelingDiaryReader;

/**
 * Created by Yuriy.Vinogradov on 13.08.2014.
 */
public class FeelingsDiaryEntry {

    public FeelingsDiaryEntry(String yearFrom, String monthFrom, String dayFrom, String hourFrom, String minuteFrom,
                             String yearTo, String monthTo, String dayTo, String hourTo, String minuteTo,
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
        this.feelingRating = feelingRating;
        this.selectedFeelings = selectedFeelings;
        this.comment = comment;
    }

    final String yearFrom;
    final String monthFrom;
    final String dayFrom;
    final String hourFrom;
    final String minuteFrom;
    final String yearTo;
    final String monthTo;
    final String dayTo;
    final String hourTo;
    final String minuteTo;
    final String feelingRating;
    final String selectedFeelings;
    final String comment;
}
