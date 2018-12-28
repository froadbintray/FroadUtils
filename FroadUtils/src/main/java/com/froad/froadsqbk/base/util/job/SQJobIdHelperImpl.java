package com.froad.froadsqbk.base.util.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

/**
 * @author Created by SimenHi.
 * @date 2018/12/28 17:06
 * @modify SimenHi
 */
class SQJobIdHelperImpl {

    public static int getJobId(Context context, String jobServiceName,int minJobId,int maxJobId) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            for (int jobId = minJobId; jobId < maxJobId; jobId++) {
                Iterator<JobInfo> infoIterator = infoList.iterator();
                boolean existJob = false;
                while (infoIterator.hasNext()) {
                    JobInfo jobInfo = infoIterator.next();

                    if (jobInfo.getId() == jobId) {
                        existJob = true;
                        break;
                    }
                }

                if (!existJob) {
                    return jobId;
                }
            }

            return SQJobIdConstants.ALL_OCCUPANCY;
        }

        return SQJobIdConstants.UNDEFINE;
    }

}
