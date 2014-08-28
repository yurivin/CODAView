package net.yvin.codaview.app.comparators.feelingsDiaryEntry;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.Comparator;

/**
 * Created by Yuriy.Vinogradov on 27.08.2014.
 */
public class ByEndDate implements Comparator<FeelingsDiaryEntry> {
    @Override
    public int compare(FeelingsDiaryEntry feelingsDiaryEntry, FeelingsDiaryEntry feelingsDiaryEntry2) {
        int result;
        if((result = Integer.parseInt(feelingsDiaryEntry.yearTo) - Integer.parseInt(feelingsDiaryEntry2.yearTo)) != 0)
            return result;
        else if((result = Integer.parseInt(feelingsDiaryEntry.monthTo) - Integer.parseInt(feelingsDiaryEntry2.monthTo)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.dayTo) - Integer.parseInt(feelingsDiaryEntry2.dayTo)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.hourTo) - Integer.parseInt(feelingsDiaryEntry2.hourTo)) != 0)
            return result;
        else  if((result = Integer.parseInt(feelingsDiaryEntry.minuteTo) - Integer.parseInt(feelingsDiaryEntry2.minuteTo)) != 0)
            return result;
        else return 0;
    }
}
