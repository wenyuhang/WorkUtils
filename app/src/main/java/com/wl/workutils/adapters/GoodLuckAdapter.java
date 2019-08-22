package com.wl.workutils.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.wl.workutils.R;
import com.wl.workutils.base.MyBaseAdapter;

import java.util.List;

/**
 * Created by ${温宇航} on 2018/4/23.
 */

public class GoodLuckAdapter extends MyBaseAdapter<String> {
    public GoodLuckAdapter(Context context, @LayoutRes int layoutRes, @NonNull List<String> list) {
        super(context, layoutRes, list);
    }

    @Override
    protected void convert(MyViewHolder holder, String s, boolean flag) {
        holder.setText(R.id.text_view,s);
    }
}
