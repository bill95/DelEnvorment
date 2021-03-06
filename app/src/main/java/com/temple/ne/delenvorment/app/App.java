package com.temple.ne.delenvorment.app;

import android.app.Application;
import android.os.SystemClock;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;
import com.temple.ne.delenvorment.data.Interceptor.TestInterceptor;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (isDebug()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

    }

   public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
