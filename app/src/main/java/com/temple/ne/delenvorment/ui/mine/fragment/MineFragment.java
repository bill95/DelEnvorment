package com.temple.ne.delenvorment.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.ui.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@Route(path = "/test/MineFragment", name = "测试fragment")
public class MineFragment extends BaseFragment {
    @BindView(R.id.img)
    ImageView img;

    Unbinder unbinder1;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        QMUIStatusBarHelper.translucent(getActivity());
//        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, container, false);
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setFullStatusBar(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    public static MineFragment newInstance() {

        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @OnClick(R.id.img)
    public void onViewClicked() {
        Toast.makeText(getContext(), "图像点击", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
