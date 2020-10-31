package com.example.instagramclone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.instagramclone.Post;
import com.example.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

//TODO: add a floating button to manage user logout

public class ProfileFragment extends PostsFragment{

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts.setItemAnimator(new SlideInUpAnimator());

        view.findViewById(R.id.fabLogout).setVisibility(View.VISIBLE);

        view.findViewById(R.id.fabLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                getActivity().finish();
            }
        });
    }

    @Override
    protected void queryPosts() {
        //clear old data before retrieving new ones
        allPosts.clear();

        // Specify the class to query, here Post
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);

        //include all information about the post
        query.include(Post.KEY_USER);
//        query.include(Post.KEY_CREATED_AT);

        //show post of current user only
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        //set limit of post that we ant to retrieve on app launch
        query.setLimit(20);

        // get the most recently created post at the top of the screen
        query.addDescendingOrder(Post.KEY_CREATED_AT);

        //retrieve all the posts
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                //if everything succeeded, iterate through all the post
                for(Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + " - Username: " + post.getUser().getUsername());
                }

                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false); // refresh is done
            }
        });
    }
}
