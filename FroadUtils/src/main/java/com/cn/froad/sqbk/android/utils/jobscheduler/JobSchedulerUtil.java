package com.cn.froad.sqbk.android.utils.jobscheduler;

import android.content.Context;
import android.os.Build;

import java.util.List;

/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 15:16
 * @modify 修改者 SimenHi
 */
public class JobSchedulerUtil {

    /**
     * @return 生成一个不大于最大整型值的id
     */
    public static int generateId() {
        //Integer.MAX_VALUE以毫秒为单位换算为时间后,大概有26天
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    /**
     * 生成一个不重复的唯一JobId
     *
     * @author Simen
     * @createtime 2018/12/13  下午4:11
     * @modify Simen
     * @describe 如果指定jobId不存在, 则返回此jobId;否则自动加1,直到生成一个新的不存在的jobId;(当遇到异常时,返回值Integer.MIN_VALUE)
     */
    public static int generateJobId(Context context, int jobId) {
        return generateJobId(context, jobId, null);
    }

    /**
     * 生成一个不重复的唯一JobId
     *
     * @author Simen
     * @createtime 2018/12/13  下午4:26
     * @modify Simen
     * @describe 如果指定jobId不存在, 则返回此jobId;否则当builder不为空时,则返回buildNew值;当builder为空时自动加1,直到生成一个新的不存在的jobId;(当遇到异常时,返回值Integer.MIN_VALUE)
     */
    public static int generateJobId(Context context, int jobId, JobIdBuilder builder) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            return JobSchedulerHelper.generateJobId(context, jobId, builder);
        }

        return Integer.MIN_VALUE;
    }

    /**
     * @author Simen
     * @createtime 2018/12/13  下午3:44
     * @modify Simen
     * @describe 返回指定job
     */
    public static Job findPendingJob(Context context, int jobId) {
        return findPendingJob(context, null, jobId);
    }

    /**
     * @author Simen
     * @createtime 2018/12/13  下午3:45
     * @modify Simen
     * @describe 返回指定job
     */
    public static Job findPendingJob(Context context, String classN, int jobId) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            return JobSchedulerHelper.findJob(context, classN, jobId);
        }

        return null;
    }

    /**
     * @param packageName packageName和classN不能为空
     * @param classN      packageName和classN不能为空
     * @author Simen
     * @createtime 2018/12/13  下午3:45
     * @modify Simen
     * @describe 返回指定同包名同类名的所有job
     */
    public static List<Job> findPendingJobs(Context context, String packageName, String classN) {
        if (packageName == null && classN == null) {
            throw new NullPointerException("packageName和classN不能同时为空");
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            return JobSchedulerHelper.findPendingJobs(context, packageName, classN);
        }

        return null;
    }

    /**
     * 取消所有类名为classN的Job任务
     * 注意:类名不能为空
     * @param context
     * @param classN
     */
    public static void cancelPendingJob(Context context,String classN){
        cancelPendingJob(context,null,classN);
    }

    /**
     * 取消所有包名为packageName的Job任务
     * 注意:包名不能为空
     * @param context
     * @param packageName
     */
    public static void cancelPendingJobByPkg(Context context,String packageName){
        cancelPendingJob(context,packageName,null);
    }

    /**
     * 取消所有包名为packageName和类名为classN的Job任务
     * 注意:类名和包名不能同时为空
     * @param context
     * @param packageName
     * @param classN
     */
    public static void cancelPendingJob(Context context,String packageName,String classN){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            if (packageName == null && classN == null) {
                throw new NullPointerException("packageName和classN不能同时为空");
            }

            JobSchedulerHelper.cancelPendingJob(context,packageName,classN);
        }
    }

    /**
     * 取消Job任务
     * @param context
     * @param jobId
     */
    public static void cancelPendingJob(Context context,int jobId){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            JobSchedulerHelper.cancelPendingJob(context,jobId);
        }
    }

}
