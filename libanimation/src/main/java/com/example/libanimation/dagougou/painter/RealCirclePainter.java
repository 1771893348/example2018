package com.example.libanimation.dagougou.painter;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.libanimation.dagougou.Action;
import com.example.libanimation.dagougou.PixelPath;
import com.example.libanimation.dagougou.PixelPoint;


/**
 * @Author: yuxingdong
 * @Time: 2018/2/9
 */

public class RealCirclePainter extends AbstractPainter implements CirclePainter {

    private static final String TAG = "RealCirclePainter";

    private PixelPath mPixelPath;

    private int mDuration;

    private float mStartAngle;

    private float mSweepAngle;

    private boolean mUseCenter;

    private float mPadding = 0;

    private RectF mRectF = new RectF();

    public RealCirclePainter() {
        this(null, 1000, 0, 360, false);
    }

    public RealCirclePainter(Painter painter) {
        this(painter, 0, 360, false);
    }

    public RealCirclePainter(Painter painter, float startAngle, float sweepAngle, boolean useCenter) {
        this(painter.getPixelPath(), painter.duration(), startAngle, sweepAngle, useCenter);
    }

    public RealCirclePainter(PixelPath pixelPath) {
        this(pixelPath, 1000, 0, 360, false);
    }

    public RealCirclePainter(PixelPath pixelPath, int duration,
                             float startAngle, float sweepAngle, boolean useCenter) {
        mPixelPath = pixelPath;
        mDuration = duration;
        mStartAngle = startAngle;
        mSweepAngle = sweepAngle;
        mUseCenter = useCenter;
    }

    @Override
    public PixelPath getPixelPath() {
        return mPixelPath;
    }

    @Override
    public int duration() {
        return mDuration;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public float startAngle() {
        return mStartAngle;
    }

    @Override
    public float sweepAngle() {
        return mSweepAngle;
    }

    @Override
    public float angle() {
        return pointList.get(0).getAngle();
    }

    @Override
    public boolean useCenter() {
        return mUseCenter;
    }

    @Override
    public void setPadding(float padding) {
        mPadding = padding;
    }

    @Override
    public float getPadding() {
        return mPadding;
    }

    @Override
    public void completeDraw(Canvas canvas) {
        canvas.drawArc(mRectF, mStartAngle, mSweepAngle, mUseCenter, paint);
    }

    @Override
    protected void realDraw(Canvas canvas) {
        canvas.drawArc(mRectF, mStartAngle, angle(), mUseCenter, paint);
    }

    @Override
    public boolean performDraw(Action action) {

        setRectF();

        PixelPoint point = pointList.get(0);
        float frac = duration() / INTERVAL;
        float div = 360 / frac;
        float angle = point.getAngle();


        if (mSweepAngle >= 0) {
            if (angle > mSweepAngle) {
                angle = mSweepAngle;
            }
            while (angle <= mSweepAngle) {

                if (!isRunning()) {
                    return false;
                }

                angle += div;
                if (!update(action, point, angle)) {
                    return false;
                }
            }

        } else {
            if (angle < mSweepAngle) {
                angle = mSweepAngle;
            }
            while (angle >= mSweepAngle) {

                if (!isRunning()) {
                    return false;
                }

                angle -= div;
                if (!update(action, point, angle)) {
                    return false;
                }
            }
        }
        return true;

    }

    public void setRectF() {

        // 以第一点为矩形左上角、最后一点点为右下角
        mRectF.set(pointList.get(0).getStartX() + getPadding(),
                pointList.get(0).getStartY() + getPadding(),
                pointList.get(pointList.size() - 1).getStartX() - getPadding(),
                pointList.get(pointList.size() - 1).getStartY() - getPadding());
    }

    private boolean update(Action action, PixelPoint point, float angle) {
        point.setAngle(angle);
        action.update(this);
        try {
            Thread.sleep(INTERVAL);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
