package com.cn.froad.sqbk.android.utils.jobscheduler;

/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 16:17
 * @modify 修改者 SimenHi
 */
public interface JobIdBuilder {

    /**
     * @author Simen
     * @createtime 2018/12/13  下午4:57
     * @modify Simen
     * @describe 当指定的jobId已存在时, 此方法会被调用.需要你返回一个可推荐的jobId
     */
    int buildNew(int existJobId, Job job);

}
