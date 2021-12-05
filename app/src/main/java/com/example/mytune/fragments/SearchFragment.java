package com.example.mytune.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytune.ParseApplication;
import com.example.mytune.Post;
import com.example.mytune.R;
import com.parse.ParseUser;

import java.util.List;


public class SearchFragment extends Fragment {
    private final String TAG = "SearchFragment";   // Tag for toasts
    private ParseUser users; // the current user
    //private SearchUsersAdapter mSearchUsersAdapter; // needs an adapter later

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}