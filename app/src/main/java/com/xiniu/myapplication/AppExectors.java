package com.xiniu.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建者：wyz
 * 创建时间：2020/4/2
 * 功能描述：线程使用的工具类
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class AppExectors {

    private final Executor mDiskIO;
    private final Executor mNetworkIO;
    private final Executor mMainThread;
    private final ScheduledThreadPoolExecutor scheduler;

    public static AppExectors Instance = null;

    public static AppExectors getInstance() {
        if (Instance == null) {
            synchronized (AppExectors.class) {
                if (Instance == null) {
                    Instance = new AppExectors();
                }
            }
        }
        return Instance;
    }

    private AppExectors() {
        this.mDiskIO = Executors.newSingleThreadScheduledExecutor();
        this.mNetworkIO = Executors.newFixedThreadPool(5);
        this.mMainThread = new MainThreadExector();
        this.scheduler = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.AbortPolicy());
    }

    private static class MainThreadExector implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

    public Executor getmDiskIO() {
        return mDiskIO;
    }

    public Executor getmMainThread() {
        return mMainThread;
    }

    public Executor getmNetworkIO() {
        return mNetworkIO;
    }

    public ScheduledThreadPoolExecutor getScheduler() {
        return scheduler;
    }
}
