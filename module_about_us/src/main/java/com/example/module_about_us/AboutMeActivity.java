package com.example.module_about_us;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.module_about_us.ui.main.AboutMeFragment;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AboutMeFragment.newInstance())
                    .commitNow();
        }
    }
}