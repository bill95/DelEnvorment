package com.temple.ne.delenvorment.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.utils.QMUIStatusBarHelper;

@Route(path = "/test/MineFragment", name = "测试fragment")
public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(getActivity());
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_mine, container, false);

    }

    public static MineFragment newInstance() {

        Bundle args = new Bundle();

        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
