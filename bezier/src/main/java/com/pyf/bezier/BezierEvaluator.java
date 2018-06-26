package com.pyf.bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/10/15
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mFlagValue;

    public BezierEvaluator(PointF pointF) {
        mFlagValue = pointF;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForQuadratic(fraction, startValue, mFlagValue, endValue);
    }
}
