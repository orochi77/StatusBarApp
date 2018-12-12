package com.tangbba.statusbarapp.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloadAsyncTask extends AsyncTask<Void, Void, Bitmap> {

    private static final String TAG = "ImageDownloadAsyncTask";

    private final Context mContext;
    private final String mImageUrl;

    private ImageDownloadAsyncTaskListener mImageDownloadAsyncTaskListener;

    public ImageDownloadAsyncTask(Context context, String imageUrl) {
        mContext = context;
        mImageUrl = imageUrl;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        return downloadedBitmap();
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
            if (getImageDownloadAsyncTaskListener() != null) {
                getImageDownloadAsyncTaskListener().onDownloadFail();
            }
            return;
        }

        if (bitmap == null) {
            if (getImageDownloadAsyncTaskListener() != null) {
                getImageDownloadAsyncTaskListener().onDownloadFail();
            }
            return;
        }

        if (getImageDownloadAsyncTaskListener() != null) {
            getImageDownloadAsyncTaskListener().onDownloadComplete(bitmap);
        }
    }

    private Bitmap downloadedBitmap() {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(mImageUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                if (getImageDownloadAsyncTaskListener() != null) {
                    getImageDownloadAsyncTaskListener().onDownloadFail();
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w(TAG, "Error download image from " + mImageUrl);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    public ImageDownloadAsyncTaskListener getImageDownloadAsyncTaskListener() {
        return mImageDownloadAsyncTaskListener;
    }

    public void setImageDownloadAsyncTaskListener(ImageDownloadAsyncTaskListener imageDownloadAsyncTaskListener) {
        mImageDownloadAsyncTaskListener = imageDownloadAsyncTaskListener;
    }

    public interface ImageDownloadAsyncTaskListener {
        void onDownloadComplete(Bitmap bitmap);
        void onDownloadFail();
    }
}
