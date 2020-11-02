package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView tvUsername;
    private ImageView ivImage;
    private TextView tvDescription;
    private TextView tvCreatedAt;
//    public String formattedTime;
    public String fullTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvUsername = findViewById(R.id.tvUsername);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvCreatedAt = findViewById(R.id.tvCreatedAt);

        //retrieve data
        Post post = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        //check if the post has all the elements
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(getBaseContext()).load(image.getUrl()).into(ivImage);
        }
        //get time of post
//        Date date = post.getCreatedAt();

        tvCreatedAt.setText(getFullTimestamp(post)); // short time 7h ago
//        tvCreatedAt.setText(post.getFullTimestamp()); // full date = tue. 27/10
    }

//    public String getFormattedTimestamp(Post post) {
//        formattedTime = TimeFormatter.getTimeDifference(post.getCreatedAt().toString());
//        return formattedTime;
//    }
    public String getFullTimestamp(Post post) {
        fullTime = TimeFormatter.getTimeStamp(post.getCreatedAt().toString());
        return fullTime;
    }
}