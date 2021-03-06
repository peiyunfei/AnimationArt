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
 * 三阶贝塞尔曲线
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/10/15
 */
public class ThirdBezier extends View {

    private boolean isSecondPoint;
    private float startPointX;
    private float startPointY;
    private float endPointX;
    private float endPointY;
    private float flagPointOneX;
    private float flagPointOneY;
    private float flagPointTwoX;
    private float flagPointTwoY;
    private Paint mTextPaint;
    private Paint mFlagPaint;
    private Paint mLinePaint;
    private Path mPath;

    public ThirdBezier(Context context) {
        this(context, null);
    }

    public ThirdBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThirdBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        flagPointOneX = w / 2 - 200;
        flagPointOneY = h / 4 - 200;
        flagPointTwoX = w / 2;
        flagPointTwoY = h / 3 - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(startPointX, startPointY);
        mPath.quadTo(flagPointOneX, flagPointOneY, endPointX, endPointY);
        canvas.drawPath(mPath, mFlagPaint);
        canvas.drawText("起点", startPointX - 85, startPointY, mTextPaint);
        canvas.drawText("控制点1", flagPointOneX, flagPointOneY, mTextPaint);
        canvas.drawText("控制点2", flagPointTwoX, flagPointTwoY, mTextPaint);
        canvas.drawText("终点", endPointX, endPointY, mTextPaint);
        canvas.drawLine(startPointX, startPointY, flagPointOneX, flagPointOneY, mLinePaint);
        canvas.drawLine(flagPointOneX, flagPointOneY, flagPointTwoX, flagPointTwoY, mLinePaint);
        canvas.drawLine(endPointX, endPointY, flagPointTwoX, flagPointTwoY, mLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_POINTER_DOWN:
                isSecondPoint = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isSecondPoint = false;
                break;
            case MotionEvent.ACTION_MOVE:
                flagPointOneX = event.getX(0);
                flagPointOneY = event.getY(0);
                if (isSecondPoint) {
                    flagPointTwoX = event.getX(1);
                    flagPointTwoY = event.getY(1);
                }
                invalidate();
                break;
        }
        return true;
    }
}
