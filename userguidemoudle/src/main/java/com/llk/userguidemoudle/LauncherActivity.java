package com.llk.userguidemoudle;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.transform.DepthTransformer;
import com.xuexiang.xui.widget.banner.transform.FadeSlideTransformer;
import com.xuexiang.xui.widget.banner.transform.FlowTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateDownTransformer;
import com.xuexiang.xui.widget.banner.transform.RotateUpTransformer;
import com.xuexiang.xui.widget.banner.transform.ZoomOutSlideTransformer;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

    private SimpleGuideBanner mSimpleGuideBanner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mSimpleGuideBanner = findViewById(R.id.sgb);

        showSimpleGuideBanner();

    }

    private void showSimpleGuideBanner() {
        mSimpleGuideBanner
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(FadeSlideTransformer.class)
                .barPadding(0, 10, 0, 10)
                .setSource(getUserGuides())//设置图片资源
                .startScroll();

        /**
         *              DepthTransformer.class,
         *             FadeSlideTransformer.class,
         *             FlowTransformer.class,
         *             RotateDownTransformer.class,
         *             RotateUpTransformer.class,
         *             ZoomOutSlideTransformer.class,
         */

        //点击跳过或者立即体验的监听
        mSimpleGuideBanner.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                //跳转到 MainActivity
                Intent intent = new Intent(LauncherActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private static List<Object> getUserGuides() {
        List<Object> list = new ArrayList<>();
        list.add(R.drawable.guide_img_1);
        list.add(R.drawable.guide_img_2);
        list.add(R.drawable.guide_img_3);
        list.add(R.drawable.guide_img_4);
        return list;
    }
}
