package com.temple.ne.delenvorment.ui.TestView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.squareup.picasso.Picasso;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.data.bean.BeanTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/activity", name = "测试专用app")
public class Test2Activity extends AppCompatActivity {
    @Autowired(name = "name")
    String key1;
    @Autowired(name = "BeanTest")
    BeanTest beanTest;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_test2);
        ButterKnife.bind(this);
        Toast.makeText(this, "beanTest.code:" + beanTest.code, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, key1, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.text)
    public void onViewClicked() {

    }
}
