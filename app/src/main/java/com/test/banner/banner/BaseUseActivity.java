package com.test.banner.banner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.geek.banner.Banner;
import com.geek.banner.loader.BannerEntry;
import com.geek.banner.loader.ImageLoader;
import com.test.banner.R;
import com.test.banner.bean.BannerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author: HSL
 * @Time: 2019/8/5 15:38
 * @E-mail: xxx@163.com
 * @Description: 基本使用~
 */
public class BaseUseActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.banner_2)
    Banner banner2;

    private List<BannerItem> mData = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, BaseUseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_use);
        ButterKnife.bind(this);
        initData();
        initBanner();
        initBanner2();
    }

    private void initData() {
        mData.add(new BannerItem(R.drawable.banner_1, ""));
        mData.add(new BannerItem(R.drawable.banner_2, ""));
        mData.add(new BannerItem(R.drawable.banner_3, ""));
        mData.add(new BannerItem(R.drawable.banner_4, ""));
        mData.add(new BannerItem(R.drawable.banner_5, ""));
    }

    private void initBanner() {
        // 1. 创建设置BannerLoader
        banner.setBannerLoader(new ImageLoader() {
            @Override
            public void loadView(Context context, BannerEntry entry, int position, View imageView) {
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.banner_default)
                        .error(R.drawable.banner_default)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                Glide.with(context).load(entry.getBannerPath()).apply(requestOptions).into((ImageView) imageView);
            }
        });

        // 2 设置页面点击事件
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void onBannerClick(int position) {
                Toast.makeText(BaseUseActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        // 3 翻页事件
        banner.setBannerPagerChangedListener(new Banner.OnBannerSimplePagerListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d("hsl777", "onPageSelected: ==>" + position);
            }
        });

        // 4 最重要一步，加载数据
        banner.loadImagePaths(mData);
    }

    private void initBanner2() {
        // 1. 创建设置BannerLoader
        banner2.setBannerLoader(new ImageLoader() {
            @Override
            public void loadView(Context context, BannerEntry entry, int position, View imageView) {
                RequestOptions requestOptions = new RequestOptions()
                        .placeholder(R.drawable.banner_default)
                        .error(R.drawable.banner_default)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                Glide.with(context).load(entry.getBannerPath()).apply(requestOptions).into((ImageView) imageView);
            }
        });

        // 2. 设置页面点击事件
        banner2.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void onBannerClick(int position) {
                Toast.makeText(BaseUseActivity.this, "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        // 3. 翻页事件
        banner2.setBannerPagerChangedListener(new Banner.OnBannerSimplePagerListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d("hsl777", "onPageSelected: ==>" + position);
            }
        });

        // 4. 最重要一步，加载数据
        banner2.loadImagePaths(mData);
    }


    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
        banner2.startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopAutoPlay();
        banner2.stopAutoPlay();
    }
}
