package com.example.huangyuwei.myapplication.ask;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.MainActivity;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.UserData;
import com.example.huangyuwei.myapplication.center;
import com.example.huangyuwei.myapplication.cure.cure_main;
import com.example.huangyuwei.myapplication.eat.eat_main;
import com.example.huangyuwei.myapplication.laugh.laugh;
import com.example.huangyuwei.myapplication.link.link;
import com.example.huangyuwei.myapplication.mem.self_main;
import com.example.huangyuwei.myapplication.move.move_main;
import com.example.huangyuwei.myapplication.talk.talk;
import com.example.huangyuwei.myapplication.user_profile;

public class ask extends ActivityWith8bigMenu {

    /*
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        /*
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // 設定 ViewPager 和 Pager Adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        */
    }

    /*
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            // 根據目前tab標籤頁的位置，傳回對應的fragment物件
            switch (position) {
                case 0:
                    fragment = new ask_group();
                    break;
                case 1:
                    fragment = new ask_msn();
                    break;
                case 2:
                    fragment = new ask_faq();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "專業線上客服";
                case 1:
                    return "即時通訊";
                case 2:
                    return "FAQ";

                default:
                    return null;
            }
        }
    }
    */
}
