package com.tangbba.statusbarapp.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.adapter.EditableIconItemRecyclerViewAdapter;
import com.tangbba.statusbarapp.adapter.helper.ItemTouchHelperCallback;
import com.tangbba.statusbarapp.base.BaseActivity;
import com.tangbba.statusbarapp.model.IconItem;

import java.util.ArrayList;
import java.util.List;

public class EditableRecyclerViewActivity extends BaseActivity {

    private static final String TAG = "EditableRecyclerViewActivity";
    public static final String EXTRA_ICON_ITEMS = "iconItems";
    public static final String EXTRA_MODIFY_ICON_ITEMS = "modifyIconItems";

    public static Intent newIntent(Context context, List<IconItem> iconItems) {
        Intent intent = new Intent(context, EditableRecyclerViewActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_ICON_ITEMS, (ArrayList<IconItem>) iconItems);

        return intent;
    }

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private EditableIconItemRecyclerViewAdapter mAdapter;
    private List<IconItem> mIconItems;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_recycler_view);

        mIconItems = getIntent().getParcelableArrayListExtra(EXTRA_ICON_ITEMS);
        findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(EXTRA_MODIFY_ICON_ITEMS, (ArrayList<IconItem>) mAdapter.getDataProvider());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setupRecyclerView(mIconItems);
    }

    private void setupRecyclerView(List<IconItem> dataProvider) {

        mAdapter = new EditableIconItemRecyclerViewAdapter(this, new ArrayList<IconItem>());
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        int spanCount = 4;
        mGridLayoutManager = new GridLayoutManager(this, spanCount);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mAdapter.setDataProvider(dataProvider);
    }
}
