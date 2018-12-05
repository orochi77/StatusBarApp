package com.tangbba.statusbarapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.adapter.IconItemRecyclerViewAdapter;
import com.tangbba.statusbarapp.base.BaseActivity;
import com.tangbba.statusbarapp.model.HomeIcons;
import com.tangbba.statusbarapp.model.IconItem;
import com.tangbba.statusbarapp.utils.IconItemGenerator;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    private static final String TAG = "RecyclerViewActivity";
    public static final int REQ_ICON_ITEM_LIST = 1000;

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
        findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditableRecyclerViewActivity.newIntent(RecyclerViewActivity.this, mHomeIcons.clone());
                startActivityForResult(intent, REQ_ICON_ITEM_LIST);
            }
        });
        setupRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ICON_ITEM_LIST) {
            if (resultCode == Activity.RESULT_OK) {
                List<IconItem> iconItems = data.getParcelableArrayListExtra(EditableRecyclerViewActivity.EXTRA_MODIFY_ICON_ITEMS);
                mHomeIcons.setIconItems(iconItems);
                mAdapter.setDataProvider(mHomeIcons.getIconItems());
            }
        }
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
