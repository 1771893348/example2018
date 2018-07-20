package com.example.p003_animation_lineview.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.libanimation.dagougou.PixelPath;
import com.example.libanimation.dagougou.SimpleLineView;
import com.example.libanimation.dagougou.painter.CirclePainter;
import com.example.libanimation.dagougou.painter.Painter;
import com.example.libanimation.dagougou.painter.RealCirclePainter;
import com.example.libanimation.dagougou.painter.SegmentPainter;
import com.example.p003_animation_lineview.R;


/**
 * @Author: yuxingdong
 * @Time: 2018/2/16
 */

public class AddSampleActivity extends AppCompatActivity {

    private SimpleLineView mView;

    private CirclePainter mCirclePainter;

    private Painter mRectPainter;

    private Painter mTrianglePainter;

    private Paint mCurrentPaint;

    {
        PixelPath circlePath = new PixelPath(5, 5, new int[]{7, 19});
        mCirclePainter = new RealCirclePainter(circlePath);

        PixelPath rectPath = new PixelPath(5, 5, new int[]{1, 5, 25, 21});
        mRectPainter = new SegmentPainter(rectPath);

        PixelPath trianglePath = new PixelPath(5, 5, new int[]{8, 17, 19});
        mTrianglePainter = new SegmentPainter(trianglePath);
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, AddSampleActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sample);
        mView = findViewById(R.id.view);
        mCurrentPaint = mCirclePainter.getPaint();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                mView.clear();
                break;
            case R.id.bt_start:
                mView.start();
                break;
            case R.id.bt_circle:
                mCurrentPaint = mCirclePainter.getPaint();
                mView.addPainter(mCirclePainter);
                break;
            case R.id.bt_rectangle:
                mCurrentPaint = mRectPainter.getPaint();
                mView.addPainter(mRectPainter);
                break;
            case R.id.bt_triangle:
                mCurrentPaint = mTrianglePainter.getPaint();
                mView.addPainter(mTrianglePainter);
                break;
            case R.id.bt_black:
                mCurrentPaint.setColor(Color.BLACK);
                break;
            case R.id.bt_red:
                mCurrentPaint.setColor(Color.RED);
                break;
            case R.id.bt_blue:
                mCurrentPaint.setColor(Color.BLUE);
                break;
            default:
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.stop();
    }
}
