package com.hisense.demo;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    private GridView mGridView;
    private ProvinceAdapter mProvinceAdapter;
    private String[] provinceNames = new String[]{"北京", "上海", "广东", "广西", "天津", "重庆", "湖北", "湖南", "河北", "河南", "山东"};
    private int[] bgColor = new int[]{R.color.color_00ff00, R.color.color_ff0000, R.color.color_ff0000, R.color.color_ffff00,
            R.color.color_8e35ef, R.color.color_303F9F, R.color.color_00ff00, R.color.color_ff0000, R.color.color_ff0000,
            R.color.color_ffff00, R.color.color_8e35ef};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        EventBus.getDefault().register(this);
        Button btnSendMsg = findViewById(R.id.button);
        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent());
            }
        });

        initView();
    }

    private void initView() {
        mGridView = (GridView) this.findViewById(R.id.grid_view);
        List<ProvinceBean> provinceList = new ArrayList<>();
        for (int i = 0; i < provinceNames.length; i++) {
            ProvinceBean provinceBean = new ProvinceBean();
            provinceBean.setName(provinceNames[i]);
            provinceBean.setColor(bgColor[i]);
            provinceList.add(provinceBean);
        }
        //适配器
        mProvinceAdapter = new ProvinceAdapter(this, provinceList);
        mGridView.setAdapter(mProvinceAdapter);
    }


    @Subscribe
    public void onEventMainThread(MessageEvent event) {
        /* Do something */
        Log.d(TAG, "onMessageEvent: 1111111111 ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
