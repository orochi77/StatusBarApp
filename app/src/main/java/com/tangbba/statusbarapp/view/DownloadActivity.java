package com.tangbba.statusbarapp.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.service.PaycoInitialService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DownloadActivity extends AppCompatActivity {

    private static final String TAG = "DownloadActivity";

    private Button mDownloadButton;
    private Button mLaunchingButton;

    private ImageView mImageView;

    public static final String ACTION_IMAGE_WRITE_COMPLETE = "com.tangbba.statusbarapp.ACTION_IMAGE_WRITE_COMPLETE";
    public static final String EXTRA_FILE_PATH = "filePath";

    private BroadcastReceiver mImageLoadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_IMAGE_WRITE_COMPLETE.equals(intent.getAction())) {
                String filePath = intent.getStringExtra(EXTRA_FILE_PATH);
                Log.e(TAG, "filePath: " + filePath);

                File splashImageFile = new File(filePath, PaycoInitialService.mSplashImageFileName);
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(splashImageFile));
                    mImageView.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Log.e(TAG, e.getMessage(), e);
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mImageView = (ImageView) findViewById(R.id.image_view);
        findViewById(R.id.download_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PaycoInitialService.getPaycoInitialService(DownloadActivity.this, PaycoInitialService.COMMAND_TYPE.FILE_DOWNLOAD.toString());
                startService(intent);
            }
        });

        File splashImageFile = new File("/data/user/0/com.tangbba.statusbarapp/app_splash", PaycoInitialService.mSplashImageFileName);
        if (splashImageFile.exists()) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(splashImageFile));
                mImageView.setImageBitmap(bitmap);

                Intent intent = PaycoInitialService.getPaycoInitialService(DownloadActivity.this, PaycoInitialService.COMMAND_TYPE.FILE_DOWNLOAD.toString());
                startService(intent);
            } catch (FileNotFoundException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            }
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_IMAGE_WRITE_COMPLETE);
        registerReceiver(mImageLoadCompleteReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mImageLoadCompleteReceiver);
    }
}
