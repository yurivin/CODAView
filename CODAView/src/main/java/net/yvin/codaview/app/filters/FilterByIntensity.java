package net.yvin.codaview.app.filters;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;
import net.yvin.codaview.app.filters.FeelingsDiaryFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 25.08.2014.
 * This algorithm allows to check intensity without partial part
 * TODO include usage of this class to user interface and test it
 */
public class FilterByIntensity implements FeelingsDiaryFilter {

    private int intensity;

    public FilterByIntensity(int intensity) {
        this.intensity = intensity;
    }

    @Override
    public void filter(List<FeelingsDiaryEntry> set) {
        List<FeelingsDiaryEntry> temp = new ArrayList<>();
        intensity = intensity * 10;
        for (FeelingsDiaryEntry entry : set) {
            int intCheck = (int)(Double.parseDouble(entry.feelingsIntensity) * 10);
            if(intensity <= intCheck && intCheck < intensity + 10)
                temp.add(entry);
        }
        set.clear();
        set.addAll(temp);
    }
}
