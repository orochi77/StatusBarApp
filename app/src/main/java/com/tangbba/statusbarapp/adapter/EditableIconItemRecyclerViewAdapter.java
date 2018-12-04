package com.tangbba.statusbarapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangbba.statusbarapp.R;
import com.tangbba.statusbarapp.model.IconItem;

import java.util.List;

public class EditableIconItemRecyclerViewAdapter
        extends RecyclerView.Adapter<EditableIconItemRecyclerViewAdapter.ViewHolder> {

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
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
    }
}
