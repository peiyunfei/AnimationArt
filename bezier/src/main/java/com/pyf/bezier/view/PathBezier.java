package com.pyf.bezier.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.pyf.bezier.BezierEvaluator;

public class PathBezier extends View implements View.OnClickListener {

    private int mStartPointX;
    private int mStartPointY;
    private int mEndPointX;
    private int mEndPointY;
    private int mFlagPointX;
    private int mFlagPointY;
    private int mMovePointX;
    private int mMovePointY;

    private Path mPath;
    private Paint mPaint;
    private Paint mCirclePaint;

    public PathBezier(Context context) {
        this(context, null);
    }

    public PathBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mStartPointX = 100;
        mStartPointY = 100;
        mEndPointX = 600;
        mEndPointY = 600;
        mFlagPointX = 300;
        mFlagPointY = 0;
        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;
        setOnClickListener(this);
    }

    public PathBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);
        canvas.drawCircle(mStartPointX, mStartPointY, 20, mCirclePaint);
        canvas.drawCircle(mEndPointX, mEndPointY, 20, mCirclePaint);
        canvas.drawCircle(mMovePointX, mMovePointY, 20, mCirclePaint);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onClick(View v) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(
                new BezierEvaluator(new PointF(mFlagPointX, mFlagPointY)),
                new PointF(mStartPointX, mStartPointY),
                new PointF(mEndPointX, mEndPointY));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mMovePointY = (int) pointF.y;
                mMovePointX = (int) pointF.x;
                invalidate();
            }
        });
        valueAnimator.setDuration(600);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
    }
}
