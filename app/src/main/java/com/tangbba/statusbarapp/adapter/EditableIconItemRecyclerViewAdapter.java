package com.tangbba.statusbarapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.adapter.helper.ItemTouchHelperAdapter;
import com.tangbba.statusbarapp.adapter.helper.ItemTouchHelperViewHolder;
import com.tangbba.statusbarapp.model.IconItem;

import java.util.Collections;
import java.util.List;

public class EditableIconItemRecyclerViewAdapter
        extends RecyclerView.Adapter<EditableIconItemRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private static final String TAG = "EditableIconItem";

    private Context mContext;
    private List<IconItem> mDataProvider;

    public EditableIconItemRecyclerViewAdapter(Context context, List<IconItem> dataProvider) {
        mContext = context;
        mDataProvider = dataProvider;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        IconItem iconItem = mDataProvider.get(position);
        holder.setIconItem(iconItem);
    }

    @Override
    public int getItemCount() {
        if (mDataProvider == null) {
            return 0;
        }
        return mDataProvider.size();
    }

    public List<IconItem> getDataProvider() {
        return mDataProvider;
    }

    public void setDataProvider(List<IconItem> dataProvider) {
        mDataProvider = dataProvider;
        notifyDataSetChanged();
        for (int i = 0; i < mDataProvider.size(); i++) {
            Log.d(TAG, mDataProvider.get(i).toString());
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mDataProvider, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mDataProvider, i, i - 1);
            }
        }
//        Collections.swap(mDataProvider, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private View mItemRootView;
        private ImageView mIconImageView;
        private TextView mIconNameTextView;

        private IconItem mIconItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemRootView = itemView.findViewById(R.id.item_root_view);
            mIconImageView = (ImageView) itemView.findViewById(R.id.icon_image_view);
            mIconNameTextView = (TextView) itemView.findViewById(R.id.icon_name_text_view);
        }

        public void setIconItem(IconItem iconItem) {
            mIconItem = iconItem;
            mIconImageView.setImageResource(mIconItem.getIconResId());
            mIconNameTextView.setText(mIconItem.getIconName());
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundResource(R.drawable.item_background_press);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundResource(R.drawable.item_background);
        }
    }
}
