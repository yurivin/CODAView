package net.yvin.codaview.app.filters;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 27.08.2014.
 * TODO include usage of this class to user interface and test it
 */
public class FilterByRate implements FeelingsDiaryFilter {

    int rate;

    public FilterByRate(int rate) {
        this.rate = rate;
    }
    @Override
    public void filter(List<FeelingsDiaryEntry> set) {
        List<FeelingsDiaryEntry> temp = new ArrayList<>();
        rate = rate * 10;
        for (FeelingsDiaryEntry entry : set) {
            int intCheck = (int)(Double.parseDouble(entry.feelingRating) * 10);
            if(rate <= intCheck && intCheck < rate + 10)
                temp.add(entry);
        }
        set.clear();
        set.addAll(temp);
    }
}
