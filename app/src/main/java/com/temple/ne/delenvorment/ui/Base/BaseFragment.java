package com.temple.ne.delenvorment.ui.Base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.utils.QMUIStatusBarHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;
    boolean isFullStatusBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, getToolBarView(container));
        return getToolBarView(container);
    }

    ViewGroup getToolBarView(ViewGroup container) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.fargment_toolbar, container, false);
        View toolbar = view.findViewById(R.id.toolbar);
       //暂时先切换toolbar的布局把，解决方案应该还是 设置沉浸式状态 后 toolbar计算高度，透明化后覆盖在背景上。
        if (isFullStatusBar()) {
            FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            fl.topMargin = QMUIStatusBarHelper.getStatusbarHeight(getContext());
            toolbar.setLayoutParams(fl);
            view.removeAllViews();
            view.addView(toolbar);
            view.addView(getChildLayoutView(getLayoutRes(), container), 0);
        } else {
//            LinearLayout linearLayout = new LinearLayout(getActivity());
//            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//
//            linearLayout.setLayoutParams(ll);
//            view.removeAllViews();
//            view = linearLayout;
//            view.addView(toolbar);
            view.addView(getChildLayoutView(getLayoutRes(), container));
        }

        return view;
    }

    View getChildLayoutView(@NonNull int res, ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(res, container, false);
    }

    protected abstract int getLayoutRes();

    public boolean isFullStatusBar() {
        return isFullStatusBar;
    }

    public void setFullStatusBar(boolean fullStatusBar) {
        isFullStatusBar = fullStatusBar;
    }
}
