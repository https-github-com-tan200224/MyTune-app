package com.example.mytune.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mytune.LoginActivity;
import com.example.mytune.Post;
import com.example.mytune.R;
import com.example.mytune.databinding.FragmentProfileBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ProfileFragment extends Fragment{

    private final String TAG = "ProfileFragment";   // Tag for toasts
    private ParseUser user; // the current user
    private List<Post> allPosts; // posts shown in the recycler view
    private File videoFile; // file for the user's uploaded profile picture
    private FragmentProfileBinding binding;

// I used binding instead
//    private TextView tvName;
//    private TextView tvDOB;
//    private TextView tvGender;
//    private TextView tvEmail;
//    private TextView tvBio;
//    private TextView tvPostCount;
//    private TextView tvFollowerCount;
//    private TextView tvFollowingCount;
//    private TextView tvUsernameToolbar;
//    private Button btnFavoriteSongs;
//    private Button btnEditBio;
//    private Button btnLogout;
//    private RecyclerView rvPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = ParseUser.getCurrentUser();


        // User information
        ParseFile photo = user.getParseFile("photo");
        if (photo != null) {
            Glide.with(this).load(photo.getUrl()).into(binding.ivProfilePhoto);
        }
        binding.tvUsernameToolbar.setText(user.getUsername());
        binding.tvName.setText(user.getString("name"));

        binding.tvDOB.setText(user.getString("dateOfBirth"));
        binding.tvGender.setText(user.getString("gender"));
        binding.tvEmail.setText(user.getEmail());
        binding.tvBio.setText(user.getString("bio"));

        binding.tvPostCount.setText(String.format(Locale.US, "%d", user.getInt("postCount")));
        binding.tvFollowerCount.setText(String.format(Locale.US, "%d", user.getInt("followerCount")));
        binding.tvFollowingCount.setText(String.format(Locale.US, "%d", user.getInt("followingCount")));
        binding.btnLogout.setOnClickListener(this::logoutOnClick);   // needs to create 2 methods for logout and editbio later
//        binding.btnEditBio.setOnClickListener(this::editBioOnClick);
//
//         Setup refresh listener which triggers new data loading
//        binding.swipeContainer.setOnRefreshListener(() -> {
//            allPosts.clear();
//            queryPosts(0);
//            binding.swipeContainer.setRefreshing(false);
//        });
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright);

//        return;
    }

    /* When the user clicks log out button, log out and return to the login page. */
    public void logoutOnClick(View v) {
        ParseUser.logOut();
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }


}
