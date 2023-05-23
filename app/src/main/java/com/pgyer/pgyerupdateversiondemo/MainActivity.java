package com.pgyer.pgyerupdateversiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pgyer.pgyerupdateversiondemo.pgyerutils.PgyCheckSoftModel;
import com.pgyer.pgyerupdateversiondemo.pgyerutils.PgyCheckoutCallBack;
import com.pgyer.pgyerupdateversiondemo.pgyerutils.PgyUpdateVersion;

public class MainActivity extends AppCompatActivity {

    private Button btnChack;
    private TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChack = findViewById(R.id.btnChack);
        tvShow = findViewById(R.id.tvShow);
        btnChack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PgyUpdateVersion.checkVersionUpdate("23ee37c03476e6cf3b9c723c42b56c41","95cba2a504c27fbd6d94c47117056f12","","1.0",new PgyCheckoutCallBack() {

                    @Override
                    public void onNewVersionExist(PgyCheckSoftModel model) {
                        tvShow.setText("有新版本："+JsonUtils.toJSONString(model));

                    }

                    @Override
                    public void onNonentityVersionExist(String error) {
                        tvShow.setText("无新版本"+error);
                    }

                    @Override
                    public void onFail(String error) {
                        tvShow.setText("请求失败："+error);
                    }
                });
            }
        });
    }
}