package net.yvin.codaview.app.activity.feelingsDiary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import net.yvin.codaview.app.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Yuriy.Vinogradov on 17.09.2014.
 */
public class ColoredExpListAdapter extends SimpleExpandableListAdapter {

    Context context;

    ColoredExpListAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.context = context;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = super.getGroupView(groupPosition, isExpanded, convertView, parent);
        if(groupPosition % 2 == 0)
            view.setBackgroundColor(context.getResources().getColor(R.color.background));
        else
            view.setBackgroundColor(context.getResources().getColor(R.color.darkgrey));
        return view;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        if(groupPosition % 2 == 0)
            view.setBackgroundColor(context.getResources().getColor(R.color.background));
        else
            view.setBackgroundColor(context.getResources().getColor(R.color.darkgrey));
        return view;
    }
}
