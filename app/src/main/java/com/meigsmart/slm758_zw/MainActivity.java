package com.meigsmart.slm758_zw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Base64;

public class MainActivity extends AppCompatActivity implements SerialPortUtil.OnDataReceiveListener {
    private EditText mData;
    private Button mSend;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = findViewById(R.id.data);
        mSend = findViewById(R.id.send);
        mResult = findViewById(R.id.result);

        SerialPortUtil.getInstance().onCreate();
        SerialPortUtil.getInstance().setOnDataReceiveListener(this);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SerialPortUtil.getInstance().sendBuffer(new String(mData.getText().toString()).getBytes());
                boolean s = SerialPortUtil.getInstance().sendCmds(mData.getText().toString());
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mResult.setText(msg.obj.toString().trim());
        }
    };

    @Override
    public void onDataReceive(byte[] buffer, int size) {
        String str = new String(buffer);
        Message msg = mHandler.obtainMessage();
        msg.obj = str;
        mHandler.sendMessage(msg);
    }
}
