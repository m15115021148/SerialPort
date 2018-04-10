package com.meigsmart.slm758_zw;

import android.content.Context;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SerialPortUtil.OnDataReceiveListener {
    private EditText mData;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = findViewById(R.id.data);
        mSend = findViewById(R.id.send);

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

    @Override
    public void onDataReceive(byte[] buffer, int size) {
        Log.d("result","buffer:"+buffer);
    }
}
