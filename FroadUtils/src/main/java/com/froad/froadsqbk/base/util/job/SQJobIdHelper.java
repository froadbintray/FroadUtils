package com.froad.froadsqbk.base.util.job;

import android.content.Context;
import android.os.Build;

/**
 * @author Created by SimenHi.
 * @date 2018/12/13 17:20
 * @modify SimenHi
 */
public class SQJobIdHelper {

    /**
     * 判断jobId是否为社区分配的id
     * @param jobId
     * @return
     */
    public static boolean isSQBKJobId(int jobId){
        if (jobId>=SQJobIdConstants.MIAOSHA_MIN_JOBID
                && jobId<=SQJobIdConstants.MIAOSHA_MAX_JOBID) {
            return true;
        } else if (jobId>=SQJobIdConstants.PAYCODE_MIN_JOBID
                && jobId<=SQJobIdConstants.PAYCODE_MAX_JOBID) {
            return true;
        } else if (jobId>=SQJobIdConstants.GESTURE_HEARTBEAT_MIN_JOBID
                && jobId<=SQJobIdConstants.GESTURE_HEARTBEAT_MAX_JOBID) {
            return true;
        } else if (jobId>=SQJobIdConstants.STATISTIC_MIN_JOBID
                && jobId<=SQJobIdConstants.STATISTIC_MAX_JOBID) {
            return true;
        }

        return false;
    }

    /**
     * @param context
     * @param jobServiceName
     * @return 返回一个不存在的jobId(取值范围在jobServiceName所指定的范围内)
     */
    public static int getJobIdWithException(Context context, String jobServiceName) throws SQJobIdException{
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            return SQJobIdConstants.UNDEFINE;
        }

        int jobId=getJobId(context,jobServiceName);

        if(jobId==SQJobIdConstants.ALL_OCCUPANCY){
            throw new SQJobIdException("所有预定义的jobId都被占用,jobServiceName:"+jobServiceName);
        }else if(jobId==SQJobIdConstants.UNDEFINE){
            throw new SQJobIdException("生成jobId失败,jobServiceName:"+jobServiceName);
        }

        return jobId;
    }

    /**
     * @param context
     * @param jobServiceName
     * @return 返回一个不存在的jobId(取值范围在jobServiceName所指定的范围内)
     */
    public static int getJobId(Context context, String jobServiceName) {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            return SQJobIdConstants.UNDEFINE;
        }

        int minJobId = SQJobIdConstants.UNDEFINE;
        int maxJobId = SQJobIdConstants.UNDEFINE;

        if (SQJobIdConstants.SQ_SDK_JOB_SERVICE_MIAOSHA.equals(jobServiceName)) {
            minJobId = SQJobIdConstants.MIAOSHA_MIN_JOBID;
            maxJobId = SQJobIdConstants.MIAOSHA_MAX_JOBID;
        } else if (SQJobIdConstants.SQ_SDK_JOB_SERVICE_PAYCODE.equals(jobServiceName)) {
            minJobId = SQJobIdConstants.PAYCODE_MIN_JOBID;
            maxJobId = SQJobIdConstants.PAYCODE_MAX_JOBID;
        } else if (SQJobIdConstants.SQ_SDK_JOB_SERVICE_GESTURE_HEARTBEAT.equals(jobServiceName)) {
            minJobId = SQJobIdConstants.GESTURE_HEARTBEAT_MIN_JOBID;
            maxJobId = SQJobIdConstants.GESTURE_HEARTBEAT_MAX_JOBID;
        } else if (SQJobIdConstants.SQ_SDK_JOB_SERVICE_STATISTIC.equals(jobServiceName)) {
            minJobId = SQJobIdConstants.STATISTIC_MIN_JOBID;
            maxJobId = SQJobIdConstants.STATISTIC_MAX_JOBID;
        }

        if (minJobId == maxJobId) {
            return minJobId;
        }

        return SQJobIdHelperImpl.getJobId(context,jobServiceName,minJobId,maxJobId);
    }

}
