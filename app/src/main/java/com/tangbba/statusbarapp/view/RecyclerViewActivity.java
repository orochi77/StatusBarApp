package com.tangbba.statusbarapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.adapter.IconItemRecyclerViewAdapter;
import com.tangbba.statusbarapp.base.BaseActivity;
import com.tangbba.statusbarapp.model.HomeIcons;
import com.tangbba.statusbarapp.model.IconItem;
import com.tangbba.statusbarapp.utils.IconItemGenerator;

import java.util.ArrayList;

public class RecyclerViewActivity extends BaseActivity {

    private static final String TAG = "RecyclerViewActivity";

    public static Intent newIntent(Context context) {
        return new Intent(context, RecyclerViewActivity.class);
    }

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    private IconItemRecyclerViewAdapter mAdapter;
    private HomeIcons mHomeIcons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSystemUIFragLightStatusBar();

        setContentView(R.layout.activity_recycler_view);
        mHomeIcons = new HomeIcons(IconItemGenerator.getIconItems());
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new IconItemRecyclerViewAdapter(this, new ArrayList<IconItem>());

        mRecyclerView.setAdapter(mAdapter);

        final int spanCount = 4;
        mGridLayoutManager = new GridLayoutManager(this, spanCount);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mAdapter.setDataProvider(mHomeIcons.getIconItems());
    }
}
