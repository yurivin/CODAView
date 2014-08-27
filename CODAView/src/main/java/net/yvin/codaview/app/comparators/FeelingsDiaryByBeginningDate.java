package net.yvin.codaview.app.comparators;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.Comparator;

/**
 * Created by Yuriy.Vinogradov on 25.08.2014.
 */
public class FeelingsDiaryByBeginningDate implements Comparator<FeelingsDiaryEntry> {
    @Override
    public int compare(FeelingsDiaryEntry feelingsDiaryEntry, FeelingsDiaryEntry feelingsDiaryEntry2) {
        int result;
        if((result = Integer.parseInt(feelingsDiaryEntry.yearFrom) - Integer.parseInt(feelingsDiaryEntry2.yearFrom)) != 0)
            return result;
        else if((result = Integer.parseInt(feelingsDiaryEntry.monthFrom) - Integer.parseInt(feelingsDiaryEntry2.monthFrom)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.dayFrom) - Integer.parseInt(feelingsDiaryEntry2.dayFrom)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.hourFrom) - Integer.parseInt(feelingsDiaryEntry2.hourFrom)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.minuteFrom) - Integer.parseInt(feelingsDiaryEntry2.minuteFrom)) != 0)
            return result;
        else return 0;
    }
}
