package com.llk.bottomdialogmoudle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;
import com.xuexiang.xui.widget.toast.XToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mShowListDialog;
    private Button mShowGridDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    //显示底部列表对话框
    private void showListDialog(){
        new BottomSheet.BottomListSheetBuilder(this,true)
                .setTitle("请选择你的性别")
                .addItem("男")//设置列表中的项，该方法既设置了选项内容又设置了选项 tag 属性。
                .addItem("女")
                .addItem("保密")
                .addItem(R.drawable.icon_more_operation_share_moment,"带图标的选项","文字tag")
                //.setIsCenter(true)//文字居中对齐
                .setCheckedIndex(2)//设置默认选中项，需要设置 needRightMark 为 true
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        XToast.normal(MainActivity.this,"您选择了 "+tag).show();
                        dialog.dismiss();//关闭对话框
                    }
                })
                //对话框关闭时执行
                //用户点击其他任意地方会关闭对话框
                .setOnBottomDialogDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        XToast.normal(MainActivity.this,"对话框关闭了").show();
                    }
                })
                .build()
                .show();
    }

    //显示宫格类型的底部对话框
    private void showGridDialog(){
        BottomSheet.BottomGridSheetBuilder builder = new BottomSheet.BottomGridSheetBuilder(this);
        builder
                .addItem(R.drawable.icon_more_operation_share_friend, "分享到微信", 0, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                //设置对话框中的某一项
                //图标资源，选项文字内容，tag，选项在对话框的第几行
                .addItem(R.drawable.icon_more_operation_share_moment, "分享到朋友圈", 1, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_weibo, "分享到微博", 2, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_share_chat, "分享到私信", 3, BottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.drawable.icon_more_operation_save, "保存到本地", 4, BottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .setOnSheetItemClickListener(new BottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, BottomSheetItemView itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        XToast.normal(MainActivity.this,"点击了第 "+ tag+" 个").show();
                    }
                })
                .build()
                .show();
    }

    private void initView(){
        mShowListDialog = findViewById(R.id.showListDialog);
        mShowGridDialog = findViewById(R.id.showGridDialog);
    }

    private void setListener(){
        mShowListDialog.setOnClickListener(this);
        mShowGridDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showListDialog:
                showListDialog();
                break;
            case R.id.showGridDialog:
                showGridDialog();
                break;
        }
    }
}
