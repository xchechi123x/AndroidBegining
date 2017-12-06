package com.xiaolaogong.test.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;

import com.xiaolaogong.navbar.BottomBarHolderActivity;
import com.xiaolaogong.navbar.NavigationPage;
import com.xiaolaogong.test.R;
import com.xiaolaogong.test.fragment.DiscoveryFragment;
import com.xiaolaogong.test.fragment.HomeFragment;
import com.xiaolaogong.test.fragment.InterestFragment;
import com.xiaolaogong.test.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BottomBarHolderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        NavigationPage home = new NavigationPage(getString(R.string.fragment_home_text),
                ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp),
                HomeFragment.newInstance());

        NavigationPage interest = new NavigationPage(getString(R.string.fragment_interest_text),
                ContextCompat.getDrawable(this, R.drawable.ic_mail_black_24dp),
                InterestFragment.newInstance());

        NavigationPage discovery = new NavigationPage(getString(R.string.fragment_discovery_text),
                ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_24dp),
                DiscoveryFragment.newInstance());

        NavigationPage user = new NavigationPage(getString(R.string.fragment_person_text),
                ContextCompat.getDrawable(this, R.drawable.ic_person_black_24dp),
                UserFragment.newInstance());

        List<NavigationPage> navigationPages = new ArrayList<>(4);

        navigationPages.add(home);
        navigationPages.add(discovery);
        navigationPages.add(interest);
        navigationPages.add(user);

        super.setupBottomBarHolderActivity(navigationPages);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
