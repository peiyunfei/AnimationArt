package com.pyf.bezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/10/15
 */
public class DrawPadView extends View {

    private float mX;
    private float mY;
    private Paint mPaint;
    private Path mPath;

    public DrawPadView(Context context) {
        super(context);
    }

    public DrawPadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    public DrawPadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float cx = (x + mX) / 2;
                float cy = (y + mY) / 2;
                mPath.quadTo(mX, mY, cx, cy);
                mX = x;
                mY = y;
                break;
        }
        invalidate();
        return true;
    }
}
