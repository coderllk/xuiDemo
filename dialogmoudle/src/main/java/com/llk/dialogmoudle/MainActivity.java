package com.llk.dialogmoudle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListAdapter;
import com.xuexiang.xui.widget.dialog.materialdialog.simplelist.MaterialSimpleListItem;
import com.xuexiang.xui.widget.dialog.strategy.InputCallback;
import com.xuexiang.xui.widget.dialog.strategy.InputInfo;
import com.xuexiang.xui.widget.dialog.strategy.impl.MaterialDialogStrategy;
import com.xuexiang.xui.widget.imageview.crop.Handle;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DialogLoader mDialogLoader;

    private Button mshowConfirmDialog;
    private Button mShowTipDialog;
    private Button mShowInputDialog;
    private Button mShowSingleChoiceDialog;
    private Button mShowMultiChoiceDialog;
    private Button mCircleLoadingProgressDialog;
    private Button mShowPictureItemDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();

        //设置对话框策略
        mDialogLoader = DialogLoader.getInstance().setIDialogStrategy(new MaterialDialogStrategy());
    }

    private void initView(){
        mShowTipDialog = findViewById(R.id.showTipDialog);
        mshowConfirmDialog = findViewById(R.id.showConfirmDialog);
        mShowInputDialog = findViewById(R.id.showInputDialog);
        mShowSingleChoiceDialog = findViewById(R.id.showSingleChoiceDialog);
        mShowMultiChoiceDialog = findViewById(R.id.showMultiChoiceDialog);
        mCircleLoadingProgressDialog = findViewById(R.id.showCircleLoadingProgressDialog);
        mShowPictureItemDialog = findViewById(R.id.showPictureItemDialog);
    }

    //显示文字提示对话框
    private void showTipDialog(){
        mDialogLoader.showTipDialog(this,R.drawable.icon_tip,"显示简要的提示对话框",
                "这是一个带图标和按钮的简单的提示信息的对话框","确认"
                ,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //点击确认按钮后关闭
                        dialogInterface.dismiss();
                        XToast.normal(MainActivity.this,"点击了确认按钮").show();
                    }
                });
    }

    //显示确认提示对话框
    private void showConfirmDialog(){
        mDialogLoader.showConfirmDialog(MainActivity.this, "这是一个确认提示框，点击取消将关闭对话框", "确认",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XToast.normal(MainActivity.this, "点击了确认按钮").show();
                        dialogInterface.dismiss();
                    }
                }
                , "取消");
    }

    //显示带输入框的对话框
    private void showInputDialog(){
        //对用户需要输入的内容做一些限制
        InputInfo inputInfo = new InputInfo(InputType.TYPE_CLASS_TEXT);
        inputInfo.setHint("hint:请输入昵称");

        mDialogLoader.showInputDialog(MainActivity.this
                , R.drawable.icon_tip,
                "请输入您的昵称", "请在输入框中输入您的昵称", inputInfo,
                new InputCallback() {
                    //
                    @Override
                    public void onInput(@NonNull DialogInterface dialog, CharSequence input) {
                        XToast.normal(MainActivity.this, input).show();
                    }
                }, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        XToast.normal(MainActivity.this, "点击了确认").show();
                        dialogInterface.dismiss();
                    }
                }, "取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        XToast.normal(MainActivity.this, "点击了取消").show();
                        /*String input = ((MaterialDialog)dialog).getInputEditText().getText().toString();
                        XToast.normal(MainActivity.this, input).show();*/
                        dialog.cancel();
                    }
                });
    }

    //显示带单选框的提示对话框
    private void showSingleChoiceDialog(){
        final String items[] = {"保密","男","女"};
        mDialogLoader.showSingleChoiceDialog(MainActivity.this
                ,"选择您的性别"
                ,items
                ,0
                ,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //点击确认按钮后，执行该方法
                        XToast.normal(MainActivity.this,"您选择了 "+items[i]).show();
                    }
                },"确认"
                ,"关闭");
    }

    //显示带多选框的提示对话框
    private void showMultiChoiceDialog(){
        final String items[] = {"唱歌","跳舞","rap","下棋","看电影","读书"};
        new MaterialDialog.Builder(MainActivity.this)
                .title("请选择您的爱好")
                .iconRes(R.drawable.icon_tip)
                .items(items)
                .itemsCallbackMultiChoice(new Integer[]{0},new MaterialDialog.ListCallbackMultiChoice(){

                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        String str = "";
                        for(int i = 0;i<which.length;i++){
                            str = text[i]+" " + str;
                        }
                        XToast.normal(MainActivity.this,"您选择了"+str).show();
                        return true;
                    }
                })

                .input("请输入","",null)
                .positiveText("确认")
                .negativeText("取消")
                .show();//该方法不能忘记
    }

    //显示带加载条的对话框
    private void showCircleLoadingProgressDialog(){
        new MaterialDialog.Builder(this)
                .iconRes(R.drawable.icon_tip)
                .limitIconToDefaultSize()
                .title("系统提示")
                .content("加载数据中，请稍后")
                .progress(true, 0)
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        final MaterialDialog dialog = (MaterialDialog)dialogInterface;
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                SystemClock.sleep(2000);
                                dialog.dismiss();
                            }
                        }.start();
                    }
                })
                .progressIndeterminateStyle(false)
                .negativeText("取消")
                .show();
    }

    //显示带图标的对话框
    private void showPictureItemDialog(){
        //使用 MaterialSimpleListItem 来设置每一项的内容
        List<MaterialSimpleListItem> list = new ArrayList<>();
        list.add(new MaterialSimpleListItem.Builder(this)
                .content("编辑")
                .icon(R.drawable.icon_edit)
                .iconPaddingDp(8)
                .build());
        list.add(new MaterialSimpleListItem.Builder(this)
                .content("添加")
                .icon(R.drawable.icon_add)
                .build());
        list.add(new MaterialSimpleListItem.Builder(this)
                .content("删除")
                .icon(R.drawable.icon_delete)
                .build());
        list.add(new MaterialSimpleListItem.Builder(this)
                .content("修改")
                .icon(R.drawable.icon_update)
                .iconPaddingDp(8)
                .build());
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(list)
                .setOnItemClickListener(new MaterialSimpleListAdapter.OnItemClickListener() {
                    @Override
                    public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                        XToast.normal(MainActivity.this,"点击了 "+ item.getContent()).show();
                    }
                });
        //创建对话框，并设置每一个选项，并显示
        new MaterialDialog.Builder(this)
                .adapter(adapter, null).show();
    }

    private void setListener(){
        mShowTipDialog.setOnClickListener(this);
        mshowConfirmDialog.setOnClickListener(this);
        mShowInputDialog.setOnClickListener(this);
        mShowSingleChoiceDialog.setOnClickListener(this);
        mShowMultiChoiceDialog.setOnClickListener(this);
        mCircleLoadingProgressDialog.setOnClickListener(this);
        mShowPictureItemDialog.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showTipDialog:
                showTipDialog();
                break;
            case R.id.showConfirmDialog:
                showConfirmDialog();
                break;
            case R.id.showInputDialog:
                showInputDialog();
                break;
            case R.id.showSingleChoiceDialog:
                showSingleChoiceDialog();
                break;
            case R.id.showMultiChoiceDialog:
                showMultiChoiceDialog();
                break;
            case R.id.showCircleLoadingProgressDialog:
                showCircleLoadingProgressDialog();
                break;
            case R.id.showPictureItemDialog:
                showPictureItemDialog();
                break;
        }
    }

}
