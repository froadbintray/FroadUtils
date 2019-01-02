package com.froad.utils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cn.froad.sqbk.android.utils.jobscheduler.JobSchedulerUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("isSupportJobIntentService:"+
                JobSchedulerUtil.isSupportJobIntentService());
    }

    public void openService(View v){

    }
}
