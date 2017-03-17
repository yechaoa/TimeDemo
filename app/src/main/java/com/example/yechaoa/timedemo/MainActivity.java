package com.example.yechaoa.timedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tv_time);

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  1.上下文
                 *  2.总时间
                 *  3.计时单位
                 *  4.控件对象
                 */
                //验证码倒计时
                TimeLastUtil timeLastUtil = new TimeLastUtil(MainActivity.this, 60000, 1000, tvTime);
                timeLastUtil.start();

                //根据指定时间，两小时倒计时
                /*long waitTime = TimeLastUtil.getRemainderTime("2017-03-17 12:20:00");//指定时间，根据需求传入时间
                TimeLastUtil timeLastUtil = new TimeLastUtil(MainActivity.this,  waitTime, 1000, tvTime);
                timeLastUtil.setType(1);
                timeLastUtil.start();*/
            }
        });

    }

}
