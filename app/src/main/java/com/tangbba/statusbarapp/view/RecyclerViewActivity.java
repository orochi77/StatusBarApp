package com.tangbba.statusbarapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.base.BaseActivity;

public class RecyclerViewActivity extends BaseActivity {

    private static final String TAG = "RecyclerViewActivity";

    public static Intent newIntent(Context context) {
        return new Intent(context, RecyclerViewActivity.class);
    }

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemUIFragLightStatusBar();

        setContentView(R.layout.activity_recycler_view);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }
}
