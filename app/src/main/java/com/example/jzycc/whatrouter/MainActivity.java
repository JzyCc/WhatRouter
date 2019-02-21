package com.example.jzycc.whatrouter;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jzycc.lib_annotation.RouterUrl;
import com.example.jzycc.lib_whatrouter.WhatRouter;

@RouterUrl(url = "main")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WhatRouter.getInstance().with(this).url("second").startActiviity();
    }
}
