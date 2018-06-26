package com.pyf.bezier.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔曲线
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/10/15
 */
public class SecondBezier extends View {

    private float startPointX;
    private float startPointY;
    private float endPointX;
    private float endPointY;
    private float flagPointX;
    private float flagPointY;
    private Paint mTextPaint;
    private Paint mFlagPaint;
    private Paint mLinePaint;
    private Path mPath;

    public SecondBezier(Context context) {
        this(context, null);
    }

    public SecondBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(40);

        mFlagPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFlagPaint.setStrokeWidth(8);
        mFlagPaint.setStyle(Paint.Style.STROKE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeWidth(5);
        mLinePaint.setColor(Color.GRAY);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startPointX = w / 4;
        startPointY = h / 4;
        endPointX = w * 3 / 4 - 100;
        endPointY = h / 4;
        flagPointX = w / 2 - 200;
        flagPointY = h / 4 - 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(startPointX, startPointY);
        mPath.quadTo(flagPointX, flagPointY, endPointX, endPointY);
        canvas.drawPath(mPath, mFlagPaint);
        canvas.drawText("起点", startPointX - 85, startPointY, mTextPaint);
        canvas.drawText("控制点", flagPointX, flagPointY, mTextPaint);
        canvas.drawText("终点", endPointX, endPointY, mTextPaint);
        canvas.drawLine(startPointX, startPointY, flagPointX, flagPointY, mLinePaint);
        canvas.drawLine(endPointX, endPointY, flagPointX, flagPointY, mLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                flagPointX = event.getX();
                flagPointY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
