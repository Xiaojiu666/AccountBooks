package com.gx.accountbooks.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gx.accountbooks.R;

public class SingleInsanceActivity extends BaseActivity {


    private String test = "SingleTopActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_first);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleInsanceActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
    }
}
