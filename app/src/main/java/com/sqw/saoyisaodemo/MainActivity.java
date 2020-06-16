package com.sqw.saoyisaodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sqw.saoyisaodemo.zbar.CaptureActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 扫 码
     */
    private Button mBtnScan;
    /**
     * Hello World!
     */
    private TextView mTvScanResult;
    /**
     * 跳 转
     */
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnScan = (Button) findViewById(R.id.btn_scan);
        mBtnScan.setOnClickListener(this);
        mTvScanResult = (TextView) findViewById(R.id.tv_scanResult);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_scan:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 101);
                break;
            case R.id.btn:
               startActivity(new Intent(MainActivity.this,Main2Activity.class));
               finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                // 扫描二维码回传
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        //获取扫描结果
                        Bundle bundle = data.getExtras();
                        String result = bundle.getString(CaptureActivity.EXTRA_STRING);
                        Log.e("1233", "onActivityResult: " + result);
                        mTvScanResult.setText("扫码结果:" + result);

                    }
                }
                break;
            default:
                break;
        }
    }
}
