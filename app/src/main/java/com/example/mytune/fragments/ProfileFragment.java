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
import com.example.mytune.PostsAdapter;
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
    private FragmentProfileBinding binding; // to bind activity with layout
    private PostsAdapter adapter; // adapter for the posts' recycler view


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

        // Set up adapter and layout of recycler view
        allPosts = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        adapter = new PostsAdapter(getContext(), allPosts);
        binding.rvPosts.setLayoutManager(layoutManager);
        binding.rvPosts.setAdapter(adapter);
        queryPosts();


        // User information
        ParseFile userPhoto = user.getParseFile("profilePhoto");
        if (userPhoto != null) {    // if 'profilePhoto' is not empty, then display that picture from parse
            Glide.with(this).load(userPhoto.getUrl()).into(binding.ivProfilePhoto);
        }
        // default profile picture if there is no picture in parse
//        binding.ivProfilePhoto.setImageResource(R.drawable.ic_baseline_person_24);

        binding.tvUsernameToolbar.setText(user.getUsername());      // get the @username
        binding.tvName.setText(user.getString("name"));         // get the name of the user
        binding.tvDOB.setText(user.getString("dateOfBirth"));   // date of birth for the user
        binding.tvGender.setText(user.getString("gender"));     // get user gender
        binding.tvEmail.setText(user.getEmail());                    // get user email
        if (binding.tvBio != null) {
            binding.tvBio.setText(user.getString("bio"));            // get user bio from parse
        }
//        binding.tvBio.setText("No Bio Yet!");   // default bio if there is no bio added in parse

        binding.tvPostCount.setText(String.format(Locale.US, "%d", user.getInt("postCount")));
        binding.tvFollowerCount.setText(String.format(Locale.US, "%d", user.getInt("followerCount")));
        binding.tvFollowingCount.setText(String.format(Locale.US, "%d", user.getInt("followingCount")));
        binding.btnLogout.setOnClickListener(this::logoutOnClick);   // needs to create 2 methods for logout and editbio later
        binding.btnEditBio.setOnClickListener(this::editBioOnClick);

        // Refresh listener
        binding.swipeContainer.setOnRefreshListener(() -> {
            allPosts.clear();
            queryPosts();
            binding.swipeContainer.setRefreshing(false);
        });
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_red_light);
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

    private void queryPosts() {

        //create a container that store the data from Post
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with Getting Posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post " + post.getDescription() + ", username: " + post.getUser().getString("username"));
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });

    }
}
