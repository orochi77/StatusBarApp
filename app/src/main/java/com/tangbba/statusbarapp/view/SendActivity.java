package com.tangbba.statusbarapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.base.BaseActivity;

public class SendActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SendActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }
}
