package com.llk.viewpagerbanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleTextBanner;
import com.xuexiang.xui.widget.banner.widget.banner.base.BaseBanner;
import com.xuexiang.xui.widget.toast.XToast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleImageBanner mSimpleImageBanner;
    private SimpleTextBanner mSimpleTextBanner;

    //banner 中的标题
    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };

    //banner 的图片资源
    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };
    private List<BannerItem> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mSimpleImageBanner = findViewById(R.id.sib_simple_banner);
        mSimpleTextBanner = findViewById(R.id.stb_simple_textBanner);

        sib_simple_usage();
        showSimpleTextBanner();

    }

    private void showSimpleTextBanner(){
        List<String> titleList = new ArrayList<>();
        for(String title:titles){
            titleList.add(title);
        }
        mSimpleTextBanner
                .setSource(titleList)
                .setOnItemClickListener(new BaseBanner.OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(View view, String item, int position) {

                    }
                })
                .startScroll();
    }

    private void sib_simple_usage() {
        mSimpleImageBanner
                .setSelectAnimClass(ZoomInEnter.class)//设置动画
                .setIndicatorStyle(0)
                .setIndicatorSelectorRes(R.drawable.ic_banner_dot_unselect,R.drawable.ic_banner_dot_select)
                .setSource(mData)
                .setOnItemClickListener(new BaseBanner.OnItemClickListener<BannerItem>() {
                    @Override
                    public void onItemClick(View view, BannerItem item, int position) {

                    }
                })
                .setIsOnePageLoop(false)//设置当页面只有一条时，是否轮播
                .startScroll();//开始滚动

    }

    private void initData(){
        mData = new ArrayList<>(urls.length);

        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            mData.add(item);
        }
    }


}
