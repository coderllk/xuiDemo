package com.llk.bannermoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.toast.XToast;

public class MainActivity extends AppCompatActivity implements BannerLayout.OnBannerItemClickListener {

    private BannerAdapter mAdapter;
    private BannerLayout mBl;

    public String[] urls = new String[]{
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",
            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBl = findViewById(R.id.bl);
        mAdapter = new BannerAdapter(this,urls);
        mBl.setAdapter(mAdapter);

        //当轮播到某一项时执行
        mBl.setOnIndicatorIndexChangedListener(new BannerLayout.OnIndicatorIndexChangedListener() {
            @Override
            public void onIndexChanged(int position) {
                XToast.normal(MainActivity.this,"轮播到了第 "+position+" 个"  ).show();
            }
        });

        //设置 item 点击事件
        mAdapter.setOnBannerItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        XToast.normal(MainActivity.this,"点击了第 "+(position+1)+" 个"  ).show();
    }
}
