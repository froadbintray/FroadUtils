package com.cn.froad.sqbk.android.utils.jobscheduler;

import java.io.Serializable;

/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 15:22
 * @modify 修改者 SimenHi
 */
public class Job implements Serializable{

    private static final long serialVersionUID = 42L;

    private int jobId;
    private String packageName;
    private String mClass;

    public Job(){

    }

    public Job(int id, String packageName, String className) {
        this.jobId=id;
        this.packageName=packageName;
        this.mClass=className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassN() {
        return mClass;
    }

    public void setClassN(String mClass) {
        this.mClass = mClass;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
