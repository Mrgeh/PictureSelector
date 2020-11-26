package com.xtjc.pictureselector;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MainActivity
 *
 * @author Ge Hang
 * @Admonish This is the ancestral code from Ge Hang, please check!
 * @time 2020/11/26 14:13
 */
public class MainActivity extends CheckPermissionsActivity {
    private int maxSelectNum = 19;
    private int code = PictureConfig.CHOOSE_REQUEST;
    private int chooseMode = PictureMimeType.ofImage();
    private List<LocalMedia> selectList = new ArrayList<>();
    private BottomDialog bottomDialog;
    private String pathes;
    @BindView(R.id.img_header_setting_bg)
    ImageView imgHeaderSettingBg;
    @BindView(R.id.lay_image)
    LinearLayout layImage;
    @BindView(R.id.view_setting_camere)
    RelativeLayout viewSettingCamere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
    }


    /**
     * 选择性别弹窗
     *
     * @param teil
     * @param textone
     * @param texttwo
     * @param i
     */
    private void showBottomDialog(String teil, String textone, String texttwo, final int i) {
        bottomDialog = new BottomDialog(this);
        bottomDialog.setTitleText(teil);
        bottomDialog.setOneText(textone);
        bottomDialog.setTwoText(texttwo);
        bottomDialog.setClicklistener(new BottomDialog.ClickListenerInterface() {
            @Override
            public void onTitleClick() {

            }

            @Override
            public void onOneClick() {
                if (i == 1) {

                } else {
                    PictureUtil.Album(MainActivity.this, chooseMode, selectList, code, maxSelectNum, false);
                }
                bottomDialog.dismissDialog();
            }

            @Override
            public void onTwoClick() {
                if (i == 1) {

                } else {
                    PictureUtil.Camera(MainActivity.this, chooseMode, selectList, code, maxSelectNum, false);
                }

                bottomDialog.dismissDialog();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的

                    for (LocalMedia media : selectList) {
                        Log.d("图片-----》", media.getPath());
                        pathes = media.getPath();
                        ShowImageUtils.showImageFitCenter(this,media.getCompressPath(),imgHeaderSettingBg);
                    }

                    break;
            }
        }

    }

    @OnClick({R.id.lay_image, R.id.view_setting_camere})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_image:
                showBottomDialog("选择图片类型", "从相册选择", "拍照", 2);
                break;
            case R.id.view_setting_camere:
                break;
        }
    }
}
