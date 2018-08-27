package com.temple.ne.delenvorment.ui.TestView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.data.bean.BeanTest;

@Route(path = "/test/activity", name = "测试专用app")
public class Test2Activity extends AppCompatActivity {
    @Autowired(name = "name")
    String key1;
    @Autowired(name = "BeanTest")
    BeanTest beanTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_test2);
        Toast.makeText(this, "beanTest.code:" + beanTest.code, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, key1, Toast.LENGTH_SHORT).show();
    }
}
