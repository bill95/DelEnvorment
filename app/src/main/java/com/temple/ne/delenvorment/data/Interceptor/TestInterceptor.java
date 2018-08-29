package com.temple.ne.delenvorment.data.Interceptor;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
//拦截器始终没有正常运行 卒
@Interceptor(priority = 7, name = "测试用拦截器")
public class TestInterceptor implements IInterceptor {
    private boolean flag;
    private Context context;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (flag) {
            callback.onContinue(postcard);
            flag=false;
        } else {
            Toast.makeText(context, "跳转登录", Toast.LENGTH_SHORT).show();
            flag=true;
        }
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        this.context = context;
    }
}
