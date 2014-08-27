package net.yvin.codaview.app.filters;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FeelingsDiaryFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 25.08.2014.
 */
public class FilterByIntensity implements FeelingsDiaryFilter {

    private int intensity;

    public FilterByIntensity(int intensity) {
        this.intensity = intensity;
    }


    @Override
    public void filter(List<FeelingsDiaryEntry> set) {
        List<FeelingsDiaryEntry> temp = new ArrayList<>();
        for (FeelingsDiaryEntry entry : set) {
            if(intensity <= Double.parseDouble(entry.feelingsIntensity) && Double.parseDouble(entry.feelingsIntensity) < intensity + 1)
                temp.add(entry);
        }
        set.clear();
        set.addAll(temp);
    }
}
