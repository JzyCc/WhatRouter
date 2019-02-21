package com.example.jzycc.whatrouter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jzycc.lib_annotation.RouterUrl;

import org.w3c.dom.Text;

@RouterUrl(url = "second")
public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_text);
        textView.setText("跳转成功～");
    }
}
