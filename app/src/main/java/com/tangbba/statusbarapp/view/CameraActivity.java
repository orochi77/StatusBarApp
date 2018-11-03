package com.tangbba.statusbarapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.base.BaseActivity;


public class CameraActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, CameraActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }
}
