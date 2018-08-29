package com.temple.ne.delenvorment.ui.main.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingCallback;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import com.temple.ne.delenvorment.R;
import com.temple.ne.delenvorment.ui.TestView.TestAc;
import com.temple.ne.delenvorment.utils.QMUIStatusBarHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    @BindView(R.id.imageview)
    ImageView imageview;
    Unbinder unbinder;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_main, container, false);
//      QMUIStatusBarHelper.translucent(getActivity());
        unbinder = ButterKnife.bind(this, view);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Activity测试通过 传递对象需要自己实现解析
//                ARouter.getInstance()
////                        .build("/test/activity")
////                        .withString("name","jack").withObject("BeanTest",new BeanTest(1))
////                        .navigation(getActivity(), new NavCallback() {
////                            @Override
////                            public void onArrival(Postcard postcard) {
////
////                            }
////
////                            @Override
////                            public void onInterrupt(Postcard postcard) {
////                                super.onInterrupt(postcard);
////                            }
////                        });
                BoxingUsing();

            }
        });
        listInit();
        return view;
    }

    private void listInit() {
        recycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleview.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_text, viewGroup, false);
                BViewHolder bViewHolder = new BViewHolder(view);
                return bViewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
                if (viewHolder instanceof BViewHolder) {

                    ((BViewHolder) viewHolder).text.setText(getdata()[i]);
                }
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewHolder.getAdapterPosition() == 0) {
                            Intent intent = new Intent(getActivity(), TestAc.class);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return getdata().length;
            }

            String[] getdata() {
                return getResources().getStringArray(R.array.items);
            }
        });


    }

    class BViewHolder extends RecyclerView.ViewHolder {
        public TextView text;


        public BViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.text);
        }
    }

    //boxing图片选择器的使用
    private void BoxingUsing() {
        BoxingMediaLoader.getInstance().init(new IBoxingMediaLoader() {
            /**
             * display thumbnail images for a ImageView.
             *
             * @param img     the display ImageView. Through ImageView.getTag(R.string.boxing_app_name) to get the absolute path of the exact path to display.
             * @param absPath the absolute path to display, may be out of date when fast scrolling.
             * @param width   the resize with for the image.
             * @param height  the resize height for the image.
             */
            @Override
            public void displayThumbnail(@NonNull ImageView img, @NonNull String absPath, int width, int height) {
                String path = "file://" + absPath;
                Picasso.with(img.getContext()).load(path).centerCrop().resize(width, height).into(img);
            }

            /**
             * display raw images for a ImageView, need more work to do. 需要自己添加callback的回调 否则会一直显示加载框
             *
             * @param img      the display ImageView.Through ImageView.getTag(R.string.boxing_app_name) to get the absolute path of the exact path to display.
             * @param absPath  the absolute path to display, may be out of date when fast scrolling.
             * @param width    the expected width, 0 means the raw width.
             * @param height   the expected height, 0 means the raw height.
             * @param callback the callback for the load result.
             */
            @Override
            public void displayRaw(@NonNull ImageView img, @NonNull String absPath, int width, int height, final IBoxingCallback callback) {
                String path = "file://" + absPath;
                RequestCreator creator = Picasso.with(img.getContext())
                        .load(path);
                if (width > 0 && height > 0) {
                    creator.transform(new BitmapTransform(width, height));
                }
                creator.into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    }

                    @Override
                    public void onError() {
                        if (callback != null) {
                            callback.onFail(null);
                        }
                    }
                });
            }
        });
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG); // Mode：Mode.SINGLE_IMG, Mode.MULTI_IMG, Mode.VIDEO
        config.needCamera(R.mipmap.ic_launcher).needGif();
//                        .withMaxCount(9); // 支持gif，相机，设置最大选图数
//                config.withMediaPlaceHolderRes(R.mipmap.ic_launcher) // 设置默认图片占位图，默认无
//                        .withAlbumPlaceHolderRes(R.mipmap.ic_launcher) // 设置默认相册占位图，默认无
//                        .withVideoDurationRes(R.mipmap.ic_launcher); // 视频模式下，时长的图标，默认无
        Boxing.of(config).withIntent(getActivity(), BoxingActivity.class).start(getActivity(), 1);

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
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult();
//        List<BaseMedia> medias = Boxing.getResult(data);
//        //注意判断null
////        if (medias!=null) {
////            medias.forEach(new Consumer<BaseMedia>() {
////                @Override
////                public void accept(BaseMedia baseMedia) {
////                    Log.d("MainFragment", baseMedia.getPath());
////                }
////            });
////        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<BaseMedia> medias = Boxing.getResult(data);
    }

    public class BitmapTransform implements Transformation {
        private final int mMaxWidth;
        private final int mMaxHeight;

        BitmapTransform(int maxWidth, int maxHeight) {
            this.mMaxWidth = maxWidth;
            this.mMaxHeight = maxHeight;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth, targetHeight;
            double aspectRatio;

            if (source.getWidth() > source.getHeight()) {
                targetWidth = mMaxWidth;
                aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                targetHeight = (int) (targetWidth * aspectRatio);
            } else {
                targetHeight = mMaxHeight;
                aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                targetWidth = (int) (targetHeight * aspectRatio);
            }

            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return mMaxWidth + "x" + mMaxHeight;
        }

    }
}
