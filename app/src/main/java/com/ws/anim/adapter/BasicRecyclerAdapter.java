package com.ws.anim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ws.anim.R;
import com.ws.anim.utils.ColorUtil;
import com.ws.anim.utils.LogUtil;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   :
 * </pre>
 */
public class BasicRecyclerAdapter extends RecyclerView.Adapter<BasicRecyclerAdapter.BasicViewHolder> {
    public static final int VIEW_PAGER_TYPE = 0;

    public static final int RECYCLER_TYPE = 1;
    private final Context mContext;

    private final int mSize;

    private final int mType;

    private int layoutId = 0;

    private int textId = 0;

    public BasicRecyclerAdapter(@NonNull Context context, int size, int type) {
        mSize = Math.max(0, size);
        mContext = context;
        mType = type;
        switchItemType(mType);
    }

    private void switchItemType(int type) {
        switch (type) {
            case VIEW_PAGER_TYPE:
                layoutId = R.layout.item_basic_viewpager_item;
                textId = R.string.basic_pager_title;
                break;
            case RECYCLER_TYPE:
                layoutId = R.layout.item_basic_recycler_item;
                textId = R.string.basic_recycler_title;
                break;
            default:
                break;
        }
    }

    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasicViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder holder, int position) {
        if (holder.tvTitleView != null) {
            holder.tvTitleView.setText(mContext.getString(textId, position));
            holder.itemView.setOnClickListener(v -> {
                LogUtil.showToast(mContext, holder.tvTitleView.getText().toString());
            });
            if (mType == RECYCLER_TYPE) {
                holder.itemView.setAlpha(0.4f);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    static class BasicViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitleView;

        public BasicViewHolder(@NonNull View itemView) {
            super(itemView);
            int randomColor = ColorUtil.getRandColor();
            itemView.setBackgroundColor(randomColor);
            tvTitleView = itemView.findViewById(R.id.tv_pager_title);
            if (tvTitleView != null)
                tvTitleView.setTextColor(ColorUtil.getFitColor(randomColor));
        }
    }
}
