package com.pyf.pathmeasure.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/10/15
 */
public class PathTracingView extends View {

    private float mLength;
    private float mAnimValue;
    private Paint mPaint;
    private Path mPath;
    private Path mDst;
    private PathMeasure mPathMeasure;

    public PathTracingView(Context context) {
        super(context);
    }

    public PathTracingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAlpha(100);
        mPath = new Path();
        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mDst = new Path();
        mPathMeasure = new PathMeasure(mPath, true);
        mLength = mPathMeasure.getLength();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public PathTracingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        mDst.lineTo(0, 0);
        float stop = mLength * mAnimValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimValue - 0.5)) * mLength));
        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, mPaint);
    }
}