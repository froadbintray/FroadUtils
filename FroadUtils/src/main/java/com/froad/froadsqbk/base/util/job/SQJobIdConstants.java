package com.froad.froadsqbk.base.util.job;

/**
 * @author Created by SimenHi.
 * @date 2018/12/13 17:10
 * @modify SimenHi
 */
public class SQJobIdConstants {

    /**
     * 秒杀服务
     */
    public static final String SQ_SDK_JOB_SERVICE_MIAOSHA = "sq_sdk_job_service_id_miaosha";
    /**
     * 付款码
     */
    public static final String SQ_SDK_JOB_SERVICE_PAYCODE = "sq_sdk_job_service_id_paycode";
    /**
     * 手势密码界面后台监听
     */
    public static final String SQ_SDK_JOB_SERVICE_GESTURE_HEARTBEAT = "sq_sdk_job_service_id_gesture_heartbeat";
    /**
     * 社区统计
     */
    public static final String SQ_SDK_JOB_SERVICE_STATISTIC = "sq_sdk_job_service_id_statistic";

    /**
     * 未定义(无效jobId)
     */
    public static final int UNDEFINE=-1;
    /**
     * 所有预定义的jobId都被占用
     */
    public static final int ALL_OCCUPANCY=-2;

    public static final int MIAOSHA_MIN_JOBID=0x3f000000;
    public static final int MIAOSHA_MAX_JOBID=0x3f000200;

    public static final int PAYCODE_MIN_JOBID=0x3f000201;
    public static final int PAYCODE_MAX_JOBID=0x3f000201;

    public static final int GESTURE_HEARTBEAT_MIN_JOBID=0x3f000401;
    public static final int GESTURE_HEARTBEAT_MAX_JOBID=0x3f000401;

    public static final int STATISTIC_MIN_JOBID=0x3f000402;
    public static final int STATISTIC_MAX_JOBID=0x3f000402;
}
