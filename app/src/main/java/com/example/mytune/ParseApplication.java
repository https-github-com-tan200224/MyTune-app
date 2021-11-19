package com.example.mytune;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DKolZInaX5yFsY4ah57U5wU2U20vDNYcY33vxmWu")
                .clientKey("qDqqSTm4apLBFGIAapLOJ3qPartLihgcf4YQ1VVF")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
}
