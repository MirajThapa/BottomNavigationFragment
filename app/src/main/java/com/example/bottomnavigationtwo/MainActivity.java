package com.example.bottomnavigationtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigationtwo.databinding.ActivityMainBinding;
import com.example.bottomnavigationtwo.fragments.ExploreFragment;
import com.example.bottomnavigationtwo.fragments.HomeFragment;
import com.example.bottomnavigationtwo.fragments.LibraryFragment;
import com.example.bottomnavigationtwo.fragments.SubscriptionFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        if(savedInstanceState == null){
            Bundle bundle = new Bundle();
            bundle.putInt("some_int",0);
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).
                    add(R.id.container,HomeFragment.class,bundle).commit();
        }

        activityMainBinding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        selectFragment(new HomeFragment());
                        return true;

                    case R.id.navigation_explore:
                        selectFragment(new ExploreFragment());
                        return true;

                    case R.id.navigation_library:
                        selectFragment(new LibraryFragment());
                        return true;

                    case R.id.navigation_subscriptions:
                        selectFragment(new SubscriptionFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    public void selectFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}