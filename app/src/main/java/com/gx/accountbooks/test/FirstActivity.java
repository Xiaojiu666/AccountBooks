package com.gx.accountbooks.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gx.accountbooks.R;

import org.w3c.dom.Text;

public class FirstActivity extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_first);
        textView = (TextView) findViewById(R.id.textView11);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSecondActivity();
            }
        });
    }

    public void toSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    private ActivityResultContract<Intent, String> activityResultContract = new ActivityResultContract<Intent, String>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Intent input) {
            return input;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == Activity.RESULT_OK) {
                return intent.getStringExtra("test_data");
            }
            return null;
        }
    };

    private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(activityResultContract, new ActivityResultCallback<String>() {
        @Override
        public void onActivityResult(String result) {
            textView.setText(result);
        }
    });
}
