package net.yvin.codaview.app.comparators;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.Comparator;

/**
 * Created by Yuriy.Vinogradov on 27.08.2014.
 */
public class FeelingsDiaryByIntensity implements Comparator<FeelingsDiaryEntry> {
    @Override
    public int compare(FeelingsDiaryEntry feelingsDiaryEntry, FeelingsDiaryEntry feelingsDiaryEntry2) {
        int result;
        if ((Float.parseFloat(feelingsDiaryEntry.feelingsIntensity) - Float.parseFloat(feelingsDiaryEntry2.feelingsIntensity)) > 0)
            result = 1;
        else if ((Float.parseFloat(feelingsDiaryEntry.feelingsIntensity) - Float.parseFloat(feelingsDiaryEntry2.feelingsIntensity)) < 0)
            result = -1;
        else result = 0;
        return result;
    }
}
