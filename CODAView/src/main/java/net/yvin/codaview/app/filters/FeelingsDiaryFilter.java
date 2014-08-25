package net.yvin.codaview.app.filters;

import net.yvin.codaview.app.activity.model.FeelingsDiaryEntry;

import java.util.Set;

/**
 * Created by Yuriy.Vinogradov on 25.08.2014.
 */
public interface FeelingsDiaryFilter {

    Set<FeelingsDiaryEntry> filter(Set<FeelingsDiaryEntry> set);
}
