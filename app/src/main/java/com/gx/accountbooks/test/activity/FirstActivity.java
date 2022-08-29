package com.gx.accountbooks.test.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gx.accountbooks.R;

import java.util.Map;

public class FirstActivity extends BaseActivity {

    public static final String TAG = "FirstActivity";

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
        Intent intent = new Intent(this, SingleTopActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void requestPermission() {
        String[] strings = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        intentActivityResultLauncherP.launch(strings);
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
    private ActivityResultLauncher<Intent> intentActivityResultLauncher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    });
    private ActivityResultLauncher<String[]> intentActivityResultLauncherP = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {

        }

//        @Override
//        public void onActivityResult(String[] result) {
//
//        }
//        @Override
//        public void onActivityResult(Boolean result) {
//            Log.d(TAG, "RequestPermission result" + result);
//        }
    });

}
