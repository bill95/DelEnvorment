package com.temple.ne.delenvorment.ui.TestView;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.temple.ne.delenvorment.MainActivity;
import com.temple.ne.delenvorment.R;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/test/qr", name = "qrcode快速使用框架" )
//https://github.com/yipianfengye/android-zxingLibrary
public class TestAc extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        ZXingLibrary.initDisplayOpinion(this);

    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.button2:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
                break;
            case R.id.button3:
//                CodeUtils.createImage();
//                CaptureFragment captureFragment = new CaptureFragment();
//                // 为二维码扫描界面设置定制化界面
//                CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
//                captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
//                    @Override
//                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                        Intent resultIntent = new Intent();
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//                        bundle.putString(CodeUtils.RESULT_STRING, result);
//                        resultIntent.putExtras(bundle);
//                        setResult(RESULT_OK, resultIntent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onAnalyzeFailed() {
//                        Intent resultIntent = new Intent();
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//                        bundle.putString(CodeUtils.RESULT_STRING, "");
//                        resultIntent.putExtras(bundle);
//                        setResult(RESULT_OK, resultIntent);
//                        finish();
//                    }
//                });
//                /**
//                 * 替换我们的扫描控件
//                 */getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
                break;
            case R.id.button4:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getApplication(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        if (requestCode == 2) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片

                    CodeUtils.analyzeBitmap(uri.getPath(), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getApplicationContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getApplicationContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
