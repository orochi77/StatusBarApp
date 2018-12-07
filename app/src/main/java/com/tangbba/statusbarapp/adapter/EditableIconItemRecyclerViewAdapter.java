package com.tangbba.statusbarapp.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.Space;
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
import com.tangbba.statusbarapp.utils.DisplayUtils;

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
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
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

    public boolean isSecondRow(int position) {
        int colCount = 4;

        if (position / colCount == 1) {
            return true;
        }
        return false;
    }

    public boolean isThirdRow(int position) {
        int colCount = 4;

        if (position / colCount == 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mDataProvider, i, i + 1);
            }
//            notifyItemRangeChanged(fromPosition, Math.abs(toPosition - fromPosition) + 1, Boolean.FALSE);
            notifyItemMoved(fromPosition, toPosition);
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mDataProvider, i, i - 1);
            }
//            notifyItemRangeChanged(toPosition, Math.abs(fromPosition - toPosition) + 1, Boolean.FALSE);
            notifyItemMoved(fromPosition, toPosition);
        }
//        Collections.swap(mDataProvider, fromPosition, toPosition);
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        private View mItemRootView;
        private View mItemContainer;
        private ImageView mIconImageView;
        private TextView mIconNameTextView;

        private Space mTopSpace;
        private Space mBottomSpace;

        private IconItem mIconItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemRootView = itemView.findViewById(R.id.item_root_view);
            mItemContainer = itemView.findViewById(R.id.item_container);
            mIconImageView = (ImageView) itemView.findViewById(R.id.icon_image_view);
            mIconNameTextView = (TextView) itemView.findViewById(R.id.icon_name_text_view);
            mTopSpace = (Space) itemView.findViewById(R.id.top_spacer);
            mBottomSpace = (Space) itemView.findViewById(R.id.bottom_spacer);
        }

        public void setIconItem(IconItem iconItem) {
            mIconItem = iconItem;
            mIconImageView.setImageResource(mIconItem.getIconResId());
            mIconNameTextView.setText(mIconItem.getIconName());

//            int position = getAdapterPosition();
//            if (isSecondRow(position)) {
//                mTopSpace.setVisibility(View.GONE);
//                mBottomSpace.setVisibility(View.VISIBLE);
//            } else if (isThirdRow(position)) {
//                mTopSpace.setVisibility(View.VISIBLE);
//                mBottomSpace.setVisibility(View.GONE);
//            } else {
//                mTopSpace.setVisibility(View.GONE);
//                mBottomSpace.setVisibility(View.GONE);
//            }

        }

        @Override
        public void onItemSelected() {
            mItemContainer.setBackgroundResource(R.drawable.item_background_press);
//            mTopSpace.setVisibility(View.VISIBLE);
//            mBottomSpace.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mItemContainer.setElevation(DisplayUtils.getPixelFromDP(mContext, 6));
//                mItemContainer.setElevation(DisplayUtils.getPixelFromDP(mContext, 8));
            }
        }

        @Override
        public void onItemClear() {
            mItemContainer.setBackgroundResource(R.drawable.item_background_normal);
//            mTopSpace.setVisibility(View.GONE);
//            mBottomSpace.setVisibility(View.GONE);
            notifyDataSetChanged();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mItemContainer.setElevation(0f);
//                mItemContainer.setTranslationZ(0f);
            }
        }
    }
}
