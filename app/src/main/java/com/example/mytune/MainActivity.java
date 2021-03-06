package com.example.mytune;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.mytune.databinding.ActivityMainBinding;
import com.example.mytune.fragments.ComposeFragment;
import com.example.mytune.fragments.PostsFragment;
import com.example.mytune.fragments.ProfileFragment;

import com.example.mytune.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final FragmentManager fragmentManager = getSupportFragmentManager();

        // Handle navigation selection
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_posts:
//                    Toast.makeText(MainActivity.this, "Posts!", Toast.LENGTH_SHORT).show();
                    fragment = new PostsFragment();
                    break;
                case R.id.action_compose:
//                    Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                    fragment = new ComposeFragment();
                    break;
                case R.id.action_search:
//                    Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                    fragment = new SearchFragment();
                    break;
                case R.id.action_profile:
//                    Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                default:
                    fragment = new ProfileFragment();
                    break;
            }
            fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            return true;
        });

        // Set default selection
        binding.bottomNavigation.setSelectedItemId(R.id.action_posts);
    }
}


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public boolean onNavigationItemReselected(@NonNull MenuItem item) {
//                Fragment fragment;
//
//                switch (item.getItemId()) {
//                    case R.id.action_home:
//                        fragment = new PostsFragment();
//                        break;
//                    case R.id.action_posts:
////                        fragment = ComposeFragment;
//                        break;
//                    case R.id.action_profile:
//                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
//                        fragment = new ProfileFragment();
//                        break;
//                    case R.id.action_compose:
//                    default:
////                        fragment = ComposeFragment;
//                        break;
//                }
//                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
//                return true;
//            }
//        });
//        //get the data
//        //queryPosts();
//    }
//
//    }
