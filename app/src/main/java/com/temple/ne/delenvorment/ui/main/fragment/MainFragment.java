package com.temple.ne.delenvorment.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.data.bean.BeanTest;
import com.temple.ne.delenvorment.utils.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    @BindView(R.id.imageview)
    ImageView imageview;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, container, false);
        QMUIStatusBarHelper.translucent(getActivity());
        unbinder = ButterKnife.bind(this, view);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/test/MineFragment")
                        .withString("name","jack").withObject("BeanTest",new BeanTest(1))
                        .navigation(getActivity(), new NavigationCallback() {
                                    @Override
                                    public void onFound(Postcard postcard) {
                                        Log.d("MainFragment", postcard.toString());
                                    }

                                    @Override
                                    public void onLost(Postcard postcard) {
                                        Log.d("MainFragment", postcard.toString());
                                    }

                                    /**
                                     * Callback after navigation.
                                     *
                                     * @param postcard meta
                                     */
                                    @Override
                                    public void onArrival(Postcard postcard) {

                            }

                            /**
                             * Callback on interrupt.
                             *
                             * @param postcard meta
                             */
                            @Override
                            public void onInterrupt(Postcard postcard) {

                            }
                        });
            }
        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
