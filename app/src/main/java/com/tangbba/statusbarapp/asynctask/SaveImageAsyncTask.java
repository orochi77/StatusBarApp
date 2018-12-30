package com.tangbba.statusbarapp.asynctask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveImageAsyncTask extends AsyncTask<Bitmap, Void, Void> {
    private static final String TAG = "SaveImageAsyncTask";

    private Context mContext;
    private String mTicketId;
    private SaveImageAsyncTaskListener mSaveImageAsyncTaskListener;

    // 파일이 저장 될 디렉토리
    private File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + File.separator + "payco");
    private String mFileName;
    private File mImageFile;

    public SaveImageAsyncTask(Context context, String ticketId) {
        mContext = context;
        mTicketId = ticketId;
//        mFileName = "payco_" + ticketId + ".png";
        mFileName = "payco_splash.png";
    }

    @Override
    protected Void doInBackground(Bitmap... params) {
        Bitmap bitmap = params[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//        mImageFile =

        try {
//            FileOutputStream outputStream = new FileOutputStream(mImageFile);
            FileOutputStream outputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();;
        } catch (IOException e) {
            Log.e(TAG, "Save file: " + e.getMessage());
            if (getSaveImageAsyncTaskListener() != null) {
                getSaveImageAsyncTaskListener().onSaveFail();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (getSaveImageAsyncTaskListener() != null) {
            FileInputStream mImageFile = null;
            try {
                mImageFile = mContext.openFileInput(mFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            getSaveImageAsyncTaskListener().onSaveComplete(mImageFile.());;
        }

        Toast.makeText(mContext, "Save Complete: " + mImageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//        Intent intent = GiftShopTicketSaveBroadcastReceiver.newIntent(mImageFile.getAbsolutePath());
//        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    public SaveImageAsyncTaskListener getSaveImageAsyncTaskListener() {
        return mSaveImageAsyncTaskListener;
    }

    public void setSaveImageAsyncTaskListener(SaveImageAsyncTaskListener saveImageAsyncTaskListener) {
        mSaveImageAsyncTaskListener = saveImageAsyncTaskListener;
    }

    public interface SaveImageAsyncTaskListener {
        void onSaveComplete(String filePath);
        void onSaveFail();
    }
}
