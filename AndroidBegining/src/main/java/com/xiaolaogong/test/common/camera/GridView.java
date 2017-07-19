package com.xiaolaogong.test.common.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chechi on 2017/7/19.
 */

public class GridView extends View {

    private Paint paint;

    private boolean showGrid = true;

    public GridView(Context context) {
        this(context, null);
    }

    public GridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAlpha(120);
        paint.setStrokeWidth(1f);
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        int width = this.getWidth();
        int height = this.getHeight();

        if (showGrid) {
            canvas.drawLine(width / 3, 0, width / 3, height, paint);
            canvas.drawLine(width * 2 / 3, 0, width * 2 / 3, height, paint);
            canvas.drawLine(0, height / 3, width, height / 3, paint);
            canvas.drawLine(0, height * 2 / 3, width, height * 2 / 3, paint);
        }
    }
}
