package com.fatihturker.seslikitap.ClassFolder;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Fatih on 3.03.2018.
 */

public class image extends AppCompatImageView {

    public image(Context context) {
        super(context);
    }

    public image(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public image(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
