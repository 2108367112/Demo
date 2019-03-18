package com.test.proj01bilibili.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.proj01bilibili.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private ListView mListView;
    private ArrayList<String> mData = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Context mContext;

    private View mView;


    public HomeFragment() {
        // Required empty public constructor
//        Log.d(TAG, "HomeFragment: ");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == mView) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        mListView = mView.findViewById(R.id.lv_home_news);
//        Log.d(TAG, "onCreateView: ");
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mView) {
            ((ViewGroup) mView.getParent()).removeView(mView);
            //从父布局中移除，它的父布局是 ViewPage 。也就是说，是从ViewPage中移除。
            // 从父容器中移除，避免重复添加
        }
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d(TAG, "onActivityCreated: ");
        mData.add("111");
        mData.add("222");
        mData.add("333");
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, mData);
        mListView.setAdapter(adapter);
    }
}
