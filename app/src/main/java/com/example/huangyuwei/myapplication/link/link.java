package com.example.huangyuwei.myapplication.link;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.CameraActivity;
import com.example.huangyuwei.myapplication.MainActivity;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.UserData;
import com.example.huangyuwei.myapplication.ask.ask;
import com.example.huangyuwei.myapplication.center;
import com.example.huangyuwei.myapplication.cure.cure_main;
import com.example.huangyuwei.myapplication.eat.eat_main;
import com.example.huangyuwei.myapplication.laugh.laugh;
import com.example.huangyuwei.myapplication.mem.self_main;
import com.example.huangyuwei.myapplication.move.move_main;
import com.example.huangyuwei.myapplication.talk.talk;
import com.example.huangyuwei.myapplication.user_profile;

public class link extends ActivityWith8bigMenu {
    ImageButton center,economic,foundation,resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        center=(ImageButton) findViewById(R.id.center);
        economic=(ImageButton) findViewById(R.id.economic);
        foundation=(ImageButton) findViewById(R.id.foundation);
        resource=(ImageButton) findViewById(R.id.resource);

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),link_center.class);
                startActivity(intent);
            }
        });
        economic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),link_economic.class);
                startActivity(intent);
            }
        });
        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),link_foundation.class);
                startActivity(intent);
            }
        });
        resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),link_resource.class);
                startActivity(intent);
            }
        });

//        mSectionsPagerAdapter = new SectionsPagerAdapter(
//                getSupportFragmentManager());
//
//        // 設定 ViewPager 和 Pager Adapter.
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

//    private class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Fragment fragment = null;
//
//            // 根據目前tab標籤頁的位置，傳回對應的fragment物件
//            switch (position) {
//                case 0:
//                    fragment = new link_foundation();
//                    break;
//                case 1:
//                    fragment = new link_center();
//                    break;
//                case 2:
//                    fragment = new link_resource();
//                    break;
//                case 3:
//                    fragment = new link_economic();
//                    break;
//                case 4:
//                    fragment = new link_sister();
//                    break;
//            }
//
//            return fragment;
//        }
//
//        @Override
//        public int getCount() {
//            return 5;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "基金會";
//                case 1:
//                    return "癌症中心";
//                case 2:
//                    return "輔具資源";
//                case 3:
//                    return "經濟補助";
//                case 4:
//                    return "志工姐妹";
//                default:
//                    return null;
//            }
//        }
//    }
}
