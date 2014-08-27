package net.yvin.codaview.app.comparators;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.Comparator;

/**
 * Created by Yuriy.Vinogradov on 27.08.2014.
 */
public class FeelingsDiaryByLikeness implements Comparator<FeelingsDiaryEntry> {
    @Override
    public int compare(FeelingsDiaryEntry feelingsDiaryEntry, FeelingsDiaryEntry feelingsDiaryEntry2) {
        float first = Float.parseFloat(feelingsDiaryEntry.feelingRating) * 10;
        float second = Float.parseFloat(feelingsDiaryEntry2.feelingRating) * 10;
        int result = (int) (first - second);
        return result;
    }
}
