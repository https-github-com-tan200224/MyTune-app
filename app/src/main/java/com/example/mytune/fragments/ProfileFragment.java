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
    private FragmentProfileBinding binding; // to bind avtivity with layout

//I used data binding instead

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
        ParseFile userPhoto = user.getParseFile("profilePhoto");
        if (userPhoto != null) {    // if 'profilePhoto' is not empty, then display that picture from parse
            Glide.with(this).load(userPhoto.getUrl()).into(binding.ivProfilePhoto);
        }
        // default profile picture if there is no picture in parse
        binding.ivProfilePhoto.setImageResource(R.drawable.ic_baseline_person_24);

        binding.tvUsernameToolbar.setText(user.getUsername());      // get the @username
        binding.tvName.setText(user.getString("name"));         // get the name of the user
        binding.tvDOB.setText(user.getString("dateOfBirth"));   // date of birth for the user
        binding.tvGender.setText(user.getString("gender"));     // get user gender
        binding.tvEmail.setText(user.getEmail());                    // get user email
        if (binding.tvBio != null) {
            binding.tvBio.setText(user.getString("bio"));            // get user bio from parse
        }
        binding.tvBio.setText("No Bio Yet!");   // default bio if there is no bio added in parse

        binding.tvPostCount.setText(String.format(Locale.US, "%d", user.getInt("postCount")));
        binding.tvFollowerCount.setText(String.format(Locale.US, "%d", user.getInt("followerCount")));
        binding.tvFollowingCount.setText(String.format(Locale.US, "%d", user.getInt("followingCount")));
        binding.btnLogout.setOnClickListener(this::logoutOnClick);   // needs to create 2 methods for logout and editbio later
        binding.btnEditBio.setOnClickListener(this::editBioOnClick);
    }

    private void editBioOnClick(View view) {
    }

    /* When the user clicks log out button, log out and return to the login page. */
    public void logoutOnClick(View v) {
        ParseUser.logOut();
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }


}
