package com.temple.ne.delenvorment.ui.main.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.utils.QMUIStatusBarHelper;

public class MainFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, container, false);
        QMUIStatusBarHelper.translucent(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        if (view != null) {
//            if (hidden) {
//                view.setFitsSystemWindows(false);
//            } else {
//                view.setFitsSystemWindows(true);
//            }
//            view.requestApplyInsets();
//        }
//        super.onHiddenChanged(hidden);
//    }
}
