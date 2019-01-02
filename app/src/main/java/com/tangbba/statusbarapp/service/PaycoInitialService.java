package com.tangbba.statusbarapp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tangbba.statusbarapp.asynctask.ImageDownloadAsyncTask;
import com.tangbba.statusbarapp.view.DownloadActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaycoInitialService extends IntentService {

    private static final String TAG = "PaycoInitialService";

    public static final String EXTRA_COMMAND_TYPE = "commandType";

    public static final String mImageUrl = "http://aos.appdev.payco.com/test/upload/upload_files/splash_06_img.png";
    public static final String mLaunchingUrl = "https://api-lnc.cloud.toast.com/launching/v2/application/fd6d8dd3a1eebeb79370a290747053812e0bf8a3bd668ea4030df071caadd780/launching";

    private static final String mDirectoryName = "splash";
    public static final String mSplashImageFileName = "payco_splash.png";

    public static Intent getPaycoInitialService(final Context context, final String type) {
        Intent intent = new Intent(context, PaycoInitialService.class);
        intent.putExtra(EXTRA_COMMAND_TYPE, type);
        return intent;
    }

    public PaycoInitialService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        String commandType = intent.getStringExtra(EXTRA_COMMAND_TYPE);
        COMMAND_TYPE type = COMMAND_TYPE.valueOf(commandType);
        switch (type) {
            case FILE_DOWNLOAD:
                startFileDownload(mImageUrl);
                break;
            case API_CALL:
                break;
        }
    }

    private void startFileDownload(String filePath) {
        downloadedBitmap(mImageUrl);
    }

    private void callLaunchingAPI(String apiUrl) {

    }

    private void downloadedBitmap(String imageUrl) {
        ImageDownloadAsyncTask task = new ImageDownloadAsyncTask(this, imageUrl);
        task.setImageDownloadAsyncTaskListener(new ImageDownloadAsyncTask.ImageDownloadAsyncTaskListener() {
            @Override
            public void onDownloadComplete(Bitmap bitmap) {
                Log.e(TAG, "Download Complete");
                String splashFilePath = saveBitmapToInternalStorage(bitmap);
                Intent intent = new Intent();
                intent.setAction(DownloadActivity.ACTION_IMAGE_WRITE_COMPLETE);
                intent.putExtra(DownloadActivity.EXTRA_FILE_PATH, splashFilePath);
                sendBroadcast(intent);
            }

            @Override
            public void onDownloadFail() {

            }
        });
        task.execute();
    }

    private String saveBitmapToInternalStorage(Bitmap bitmap) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir(mDirectoryName, Context.MODE_PRIVATE);
        File splashImageFile = new File(directory, mSplashImageFileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(splashImageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return directory.getAbsolutePath();

    }

//    private void saveFile(Bitmap bitmap) {
//        SaveImageAsyncTask task = new SaveImageAsyncTask(this, "Splash");
//        task.setSaveImageAsyncTaskListener(new SaveImageAsyncTask.SaveImageAsyncTaskListener() {
//            @Override
//            public void onSaveComplete(String filePath) {
//                Log.e(TAG, "Save Complete");
//                Intent intent = new Intent();
//                intent.setAction(DownloadActivity.ACTION_IMAGE_WRITE_COMPLETE);
//                intent.putExtra(DownloadActivity.EXTRA_FILE_PATH, filePath);
//                sendBroadcast(intent);
//            }
//
//            @Override
//            public void onSaveFail() {
//
//            }
//        });
//        task.execute(bitmap);
//    }

    public enum COMMAND_TYPE {
        FILE_DOWNLOAD,
        API_CALL
    }
}
