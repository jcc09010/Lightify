package com.sideprojects.jc.lightify.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by justin.chu on 2/6/17.
 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public MarginItemDecoration(int margin){
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = margin;
        outRect.top = margin;
        outRect.left = margin;
        outRect.right = margin;
    }
}
