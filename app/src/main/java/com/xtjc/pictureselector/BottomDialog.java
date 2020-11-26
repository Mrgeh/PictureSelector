package com.xtjc.pictureselector;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;

import java.util.List;


public class BottomDialog extends Dialog {
    private TextView mTitleTv;
    private TextView mOneTv;
    private TextView mTwoTv;
    private TextView mCancelTv;
    private Dialog myDialog;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        void onTitleClick();//点击标题TextView

        void onOneClick();//点击第一个TextView

        void onTwoClick();//点击第二个TextView
    }

    public BottomDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BottomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        myDialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        //填充对话框的布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom, null);
        //初始化控件
        mTitleTv = view.findViewById(R.id.tv_dialog_title);
        mOneTv = view.findViewById(R.id.tv_dialog_one);
        mTwoTv = view.findViewById(R.id.tv_dialog_two);
        mCancelTv = view.findViewById(R.id.tv_dialog_cancel);

        mTitleTv.setOnClickListener(new DialogClickListener());
        mOneTv.setOnClickListener(new DialogClickListener());
        mTwoTv.setOnClickListener(new DialogClickListener());
        mCancelTv.setOnClickListener(new DialogClickListener());

        //将布局设置给Dialog
        myDialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = myDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);

        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (dialogWindow.getWindowManager().getDefaultDisplay().getWidth() * 0.95);
        lp.y = 20; //设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp); //将属性设置给窗体
        myDialog.show();//显示对话框
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class DialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_dialog_title:
                    clickListenerInterface.onTitleClick();
                    break;
                case R.id.tv_dialog_one:
                    clickListenerInterface.onOneClick();
                    break;
                case R.id.tv_dialog_two:
                    clickListenerInterface.onTwoClick();
                    break;
                case R.id.tv_dialog_cancel:
                    myDialog.dismiss();
                    break;
            }
        }
    }

    public void dismissDialog() {
        if (myDialog != null && myDialog.isShowing()) {
            myDialog.dismiss();
        }
    }

    /**
     * 设置标题栏文本文字
     *
     * @param stringId
     * @see #setTitleText(String)
     */
    public void setTitleText(@StringRes int stringId) {
        setTitleText(getContext().getString(stringId));
    }

    /**
     * 设置标题栏文本文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        mTitleTv.setText(text);
//        mTitleTv.setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
        mTitleTv.setVisibility(View.VISIBLE);
    }

    /**
     * 设置第一个TextView文字
     *
     * @param stringId
     */
    public void setOneText(@StringRes int stringId) {
        setOneText(getContext().getString(stringId));
    }

    /**
     * 设置第一个TextView文字
     *
     * @param text
     */
    public void setOneText(String text) {
        mOneTv.setText(text);
        mOneTv.setVisibility(View.VISIBLE);
    }

    /**
     * 设置第二个TextView文字
     *
     * @param stringId
     */
    public void setTwoText(@StringRes int stringId) {
        setTwoText(getContext().getString(stringId));
    }

    /**
     * 设置第二个TextView文字
     *
     * @param text
     */
    public void setTwoText(String text) {
        mTwoTv.setText(text);
        mTwoTv.setVisibility(View.VISIBLE);
//        mTwoTv.setTextColor(ContextCompat.getColor(getContext(),R.color.text_white));
    }

    /**
     * 获取标题栏文本
     *
     * @return
     */
    public final TextView getTitleTv() {
        return mTitleTv;
    }

    /**
     * 获取第一个文本
     *
     * @return
     */
    public final TextView getOneTv() {
        return mOneTv;
    }

    /**
     * 获取第二个文本
     *
     * @return
     */
    public final TextView getTwoTv() {
        return mTwoTv;
    }


    /**
     * 设置字体颜色
     *
     * @param titleColor 标题的颜色,默认是灰色
     * @param otherColor 其他的颜色，默认是蓝色
     * @param i          有2种模式，1（标题和其他颜色不一样）2（标题和其他颜色一样，取消键不一样）
     */
    public void setColor(int titleColor, int otherColor, int i) {
        if (i == 1) {
            mTitleTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
            mOneTv.setTextColor(ContextCompat.getColor(getContext(), otherColor));
            mTwoTv.setTextColor(ContextCompat.getColor(getContext(), otherColor));
        }
        if (i == 2) {
            mTitleTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
            mOneTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
            mTwoTv.setTextColor(ContextCompat.getColor(getContext(), titleColor));
            mCancelTv.setTextColor(ContextCompat.getColor(getContext(), otherColor));
        }
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }
}
