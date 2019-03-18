package com.test.proj01bilibili.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.proj01bilibili.R;
import com.test.proj01bilibili.adapter.ViewPageAdapter;
import com.test.proj01bilibili.foregroundService.ForegroundService;
import com.test.proj01bilibili.fragment.ChannelFragment;
import com.test.proj01bilibili.fragment.HomeFragment;
import com.test.proj01bilibili.fragment.MemberFragment;
import com.test.proj01bilibili.fragment.NewsFragment;
import com.test.proj01bilibili.onePixel.ScreenStateManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private Toolbar mTooBar;
    //侧边导航栏
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initNavigationView();
        initViewPage();
        //注册 屏幕状态 广播
        ScreenStateManager.getInstance().registerScreenStateReceiver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenStateManager.getInstance().unRegisterScreenStateReceiver(this);
    }

    /*******    NavigationView 的初始化     ********/
    //NavigationView 的 xml 布局 已经在 控件中指定
    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_call:
                        Toast.makeText(MainActivity.this, "打电话", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "friends", Toast.LENGTH_SHORT).show();
                        //发送通知
//                        Notification notification = new Notification(R.mipmap.ic_launcher, "哔哩哔哩", System.currentTimeMillis());
//                        Notification notification = new Notification();
//                        notification.defaults = Notification.DEFAULT_ALL;
//                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        manager.notify(1, notification);
                        //启动前台服务
                        Intent intent1 = new Intent(MainActivity.this, ForegroundService.class);
                        startService(intent1);
                        break;
                    case R.id.nav_mail:
                        Toast.makeText(MainActivity.this, "22222", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    /*******    ViewPage 的初始化     ********/
    private ViewPager mViewPage;

    private void initViewPage() {
        //第一步：找到ViewPage的实例
        mViewPage = (ViewPager) findViewById(R.id.view_page);
        mViewPage.setOffscreenPageLimit(3);
        //第二步：准备Adapter需要的数据
        ArrayList<Fragment> fragmentsList = new ArrayList<>();
        fragmentsList.add(new HomeFragment());
        fragmentsList.add(new ChannelFragment());
        fragmentsList.add(new NewsFragment());//动态
        fragmentsList.add(new MemberFragment());//会员购
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), fragmentsList);
        //第三步：给ViewPage设置适配器
        mViewPage.setAdapter(adapter);
    }

    /**********************  ToolBar 相关代码   ***************************/

    private void initToolBar() {
        mTooBar = (Toolbar) findViewById(R.id.toolbar);
        //如果style中没有actionbar，则 actionBar会空。
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        } else {
            setSupportActionBar(mTooBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);//toolbar去除默认title显示
        }
    }

    //toolbar的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                //HomeAsUp 按钮的id 永远是: android.R.id.home
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.menu_call: {
                Toast.makeText(MainActivity.this, "click call button", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }

    //toolbar 布局xml 解析
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
