package com.gx.accountbooks.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gx.accountbooks.R;

public class SecondActivity extends AppCompatActivity {


    private String test = "Reading is a happy thing. For those who love reading, once they read it,\n" +
            "they can't stop, they can't give up reading, they want to read to the \n" +
            "ends of the world. Some people say: \"the deepest and most peaceful \n" +
            "happiness Calm down, open the book, the deposition \n" +
            "of ink a little bit over the space, those dusty happiness a little bit \n" +
            "open. Reading is really fun.";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_first);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("test_data", test);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
