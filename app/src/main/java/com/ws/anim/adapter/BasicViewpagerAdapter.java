package com.ws.anim.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   :
 * </pre>
 */
public class BasicViewpagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;

    public BasicViewpagerAdapter() {
    }

    public BasicViewpagerAdapter(ArrayList<View> viewLists) {
        super();
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewLists.get(position));
    }
}
