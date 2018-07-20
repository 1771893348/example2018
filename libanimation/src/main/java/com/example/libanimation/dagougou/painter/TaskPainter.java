package com.example.libanimation.dagougou.painter;


import android.support.annotation.NonNull;

import com.example.libanimation.dagougou.Action;
import com.example.libanimation.dagougou.Chain;
import com.example.libanimation.dagougou.PixelPoint;
import com.example.libanimation.dagougou.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.libanimation.dagougou.SimpleLineView.STATUS_START;


/**
 * @Author: yuxingdong
 * @Time: 2018/2/9
 */

public class TaskPainter extends DelayPainter {

    private static final ThreadFactory FACTORY = new ThreadFactory() {

        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "SimpleLineView thread pool->" + mCount.getAndIncrement());
        }
    };

    private static final ThreadPoolExecutor EXECUTOR =
            new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), FACTORY);

    private final Map<Painter, List<PixelPoint>> mPainterPool;

    private boolean mIsRunning;

    public TaskPainter(Map<Painter, List<PixelPoint>> painters) {
        super(0);
        mPainterPool = painters;
    }

    @Override
    public void start(final Chain chain, final Action action) {
        mIsRunning = true;
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                // 当status为start时重置point
                if (action.getStatus() == STATUS_START) {
                    Utils.resetPointStatus(mPainterPool);
                }
                chain.proceed();
                mIsRunning = false;
            }
        });
    }

    @Override
    public boolean isRunning() {
        return mIsRunning;
    }
}
