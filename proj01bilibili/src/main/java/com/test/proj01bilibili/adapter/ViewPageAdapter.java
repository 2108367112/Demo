package com.test.proj01bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentlist;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(FragmentManager fm, ArrayList<Fragment> mFragmentlist) {
        super(fm);
        this.mFragmentlist = mFragmentlist;
    }

    //从这一项，可以看出获取的Item项必须是 Fragment 对象。
    @Override
    public Fragment getItem(int i) {
        return mFragmentlist.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentlist.size();
    }
}
