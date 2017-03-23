package com.example.yechaoa.timedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yechaoa on 2016/11/11.
 */

public class TimeLastUtil extends CountDownTimer {

    private Context mActivity;
    private TextView btn;
    private int type = 0;

    /**
     *  1.上下文
     *  2.总时间
     *  3.计时单位
     *  4.控件对象
     */
    public TimeLastUtil(Context mActivity, long millisInFuture, long countDownInterval, TextView btn) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.btn = btn;
    }

    /**
     *  根据设置的间隔时间循环调用
     * @param millisUntilFinished
     */
    private int length;
    @Override
    public void onTick(long millisUntilFinished) {
        if (type == 1) {
            int mi = 1000 * 60;
            //计算分钟
            long minute = millisUntilFinished / mi;
            //总时间-分钟的毫秒数得到秒数
            long second = (millisUntilFinished - minute * mi) / 1000;
            //计算小时
            long hour = minute / 60;
            long showMinute = minute - hour * 60;
            String strHour = hour < 10 ? "0" + hour : "" + hour;
            //分
            String strMinute = minute < 10 ? "0" + showMinute : "" + showMinute;
            //秒
            String strSecond = second < 10 ? "0" + second : "" + second;
            // 设置倒计时时间
            btn.setText("倒计时:" + strHour + ":" + strMinute + ":" + strSecond);
        } else {
            // 设置背景为灰色，这时是不能点击的
            btn.setClickable(false);
            btn.setBackgroundColor(Color.GRAY);
            // 设置倒计时时间
            btn.setText(millisUntilFinished / 1000 + "s后重新获取");
            // 获取按钮的文字
            Spannable span = new SpannableString(btn.getText().toString());
            if(millisUntilFinished / 1000>9){
                length=2;
            }else{
                length=1;
            }
            // 将倒计时时间显示为红色
            span.setSpan(new ForegroundColorSpan(Color.RED), 0, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            btn.setText(span);
        }
    }

    /**
     *  结束时调用
     */
    @SuppressLint("NewApi")
    @Override
    public void onFinish() {
        if (type == 1) {//两小时倒计时
            btn.setText("倒计时结束");
            btn.setTextColor(Color.WHITE);
            btn.setEnabled(false);
        } else {//验证码倒计时
            btn.setText("重新获取验证码");
            btn.setClickable(true);
            // 还原背景色
            btn.setBackgroundColor(Color.parseColor("#ff6600"));
        }
    }

    /**
     *  设置类型
     * @param mType
     */
    public void setType(int mType) {
        this.type = mType;
    }

    /**
     *  根据指定时间进行两小时倒计时
     * @param insertTime
     * @return
     */
    public static long getRemainderTime(String insertTime) {
        long waitTime = 0;
        //时间格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        String date1 = sdf.format(curDate);
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(insertTime);
            /**
             *  当前时间 - 指定的时间 = 已经过去的时间
             *    两小时 - 过去的时间 = 开始计时的时间
             */
            waitTime = (2 * 60 * 60 * 1000) - (d1.getTime() - d2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return waitTime;
    }

}
