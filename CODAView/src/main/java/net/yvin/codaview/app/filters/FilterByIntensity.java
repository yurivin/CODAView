package net.yvin.codaview.app.filters;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.List;
import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 25.08.2014.
 */
public class FilterByIntensity implements FeelingsDiaryFilter {

    private int intensity;

    FilterByIntensity(int intensity) {
        this.intensity = intensity;
    }


    @Override
    public void filter(List<FeelingsDiaryEntry> set) {

    }
}
