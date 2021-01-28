package com.istone.testbuy.base;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;

import com.blankj.utilcode.util.SizeUtils;

/**
 * Created by Ruansu
 * on 2020/7/22 4:04 PM
 * <p>
 * recycleView的item间距通用类
 */
public class SpaceItemDecoration extends ItemDecoration {

    private float size;
    private DecorationType type;

    public SpaceItemDecoration(float size, DecorationType type) {
        this.size = size;
        this.type = type;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        switch (type) {
            case ALL:
                outRect.top = outRect.right = outRect.bottom = outRect.left = SizeUtils.dp2px(size);
                break;
            case TOP:
                outRect.top = SizeUtils.dp2px(size);
                break;
            case LEFT:
                outRect.left = SizeUtils.dp2px(size);
                break;
            case RIGHT:
                outRect.right = SizeUtils.dp2px(size);
                break;
            case BOTTOM:
                outRect.bottom = SizeUtils.dp2px(size);
                break;
            case TOP_LEFT:
                outRect.top = outRect.left = SizeUtils.dp2px(size);
                break;
            case TOP_RIGHT:
                outRect.top = outRect.right = SizeUtils.dp2px(size);
                break;
            case BOTTOM_LEFT:
                outRect.bottom = outRect.left = SizeUtils.dp2px(size);
                break;
            case BOTTOM_RIGHT:
                outRect.bottom = outRect.right = SizeUtils.dp2px(size);
                break;
        }
    }

    public enum DecorationType {
        ALL, TOP_LEFT, TOP_RIGHT,
        BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
    }

}
