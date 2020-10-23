package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register the parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("NRG2A7VwaJJJMGkjNKURHFDi6sex0MWTKUdjyr9T")
                .clientKey("7HwBHK4GXfYQbGEDhEvkdV0sppNnD09nppWJqfdV")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
