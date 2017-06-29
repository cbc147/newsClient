package com.example.newsclient.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.newsclient.R;
import com.example.newsclient.MainFragment1;
import com.example.newsclient.MainFragment2;
import com.example.newsclient.MainFragment3;
import com.example.newsclient.MainFragment4;
import com.example.newsclient.MainFragment5;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private ViewPager view_pager;
    private RadioGroup rg_01;
    private DrawerLayout drawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle toggle;



    @Override
    public void initView() {
        rg_01 = (RadioGroup) findViewById(R.id.rg_buttom);
        initViewPager();
        initNavigationView();
        initToolbar();
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        toggle = new ActionBarDrawerToggle(this , drawerLayout , mToolbar ,
                R.string.navigation_drawer_open ,
                R.string.navgation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle("广交实训");
        mToolbar.setTitleTextColor(Color.WHITE);

//        // 导航栏图标显示
//        mToolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
//
//        // 点击toolbar导航栏左上角的图标后，退出当前界面
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            showToast(""+item.getTitle());
            drawerLayout.closeDrawers();
            return false;
        }
    });
    }

    private void initViewPager() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment1());
        fragments.add(new MainFragment2());
        fragments.add(new MainFragment3());
        fragments.add(new MainFragment4());
        fragments.add(new MainFragment5());

        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        rg_01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_01:    // 点击单选时，切换ViewPager子界面
                        view_pager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:
                        view_pager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:
                        view_pager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:
                        view_pager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:
                        view_pager.setCurrentItem(4);
                        break;
                }
            }
        });
        view_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {  // 当ViewPager子界面切换后，选中某个RadioButton
                    case 0:
                        rg_01.check(R.id.rb_01);
                        break;
                    case 1:
                        rg_01.check(R.id.rb_02);
                        break;
                    case 2:
                        rg_01.check(R.id.rb_03);
                        break;
                    case 3:
                        rg_01.check(R.id.rb_04);
                        break;
                    case 4:
                        rg_01.check(R.id.rb_05);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item_01) {
            showToast("item 01");
            return true;
        }
        if (item.getItemId() == R.id.item_02) {
            showToast("item 02");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
