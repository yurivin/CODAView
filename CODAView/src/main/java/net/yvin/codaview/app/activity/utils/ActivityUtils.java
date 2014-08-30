package net.yvin.codaview.app.activity.utils;

import java.util.List;

/**
 * Created by Yuriy.Vinogradov on 29.08.2014.
 */
public class ActivityUtils {

    public static void handleMultiChoiceItemClick(int which, boolean isChecked, List<Integer> filterNumbers) {
        if (isChecked == true)
            filterNumbers.add(which);
        else
            for (int i = 0; i < filterNumbers.size(); i++)
                if (filterNumbers.get(i) == which)
                    filterNumbers.remove(i);
    }
}
