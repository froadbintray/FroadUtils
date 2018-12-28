package com.cn.froad.sqbk.android.utils.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 15:17
 * @modify 修改者 SimenHi
 */
class JobSchedulerHelper {

    public static Job findJob(Context context, String classN, int jobId) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            Iterator<JobInfo> infoIterator = infoList.iterator();

            while (infoIterator.hasNext()) {
                JobInfo jobInfo = infoIterator.next();

                if (jobInfo.getId() == jobId) {
                    ComponentName componentName = jobInfo.getService();

                    if (classN != null && !classN.equals(componentName.getClassName())) {
                        return null;
                    }

                    Job job = new Job();
                    job.setJobId(jobId);
                    job.setPackageName(componentName.getPackageName());
                    job.setClassN(componentName.getClassName());

                    return job;
                }
            }
        }

        return null;
    }

    public static List<Job> findPendingJobs(Context context, String packageName, String classN) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        List<Job> jobList = new ArrayList<Job>();

        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            Iterator<JobInfo> infoIterator = infoList.iterator();

            while (infoIterator.hasNext()) {
                JobInfo jobInfo = infoIterator.next();
                ComponentName componentName = jobInfo.getService();

                if (packageName != null && packageName.equals(componentName.getPackageName())) {
                    jobList.add(new Job(jobInfo.getId(), componentName.getPackageName(), componentName.getClassName()));
                } else if (classN != null && classN.equals(componentName.getClassName())) {
                    jobList.add(new Job(jobInfo.getId(), componentName.getPackageName(), componentName.getClassName()));
                }

            }
        }

        return jobList;
    }

    public static int generateJobId(Context context, int jobId, JobIdBuilder builder) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            boolean exist = false;
            int meJobId = jobId;
            Job job = new Job();

            do {
                exist = false;

                Iterator<JobInfo> infoIterator = infoList.iterator();
                while (infoIterator.hasNext()) {
                    JobInfo jobInfo = infoIterator.next();

                    if (jobInfo.getId() == meJobId) {
                        exist = true;
                        ComponentName componentName = jobInfo.getService();

                        job.setJobId(jobId);
                        job.setPackageName(componentName.getPackageName());
                        job.setClassN(componentName.getClassName());
                        break;
                    }
                }

                if (exist) {
                    if (builder != null) {
                        meJobId = builder.buildNew(meJobId, job);
                    } else {
                        if (meJobId + 1 >= Integer.MAX_VALUE) {
                            meJobId = 0;
                        } else {
                            meJobId++;
                        }
                    }
                }
            } while (exist);

            return meJobId;
        }

        return Integer.MIN_VALUE;
    }

    public static void cancelPendingJob(Context context, String packageName, String classN) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            Iterator<JobInfo> infoIterator = infoList.iterator();

            while (infoIterator.hasNext()) {
                JobInfo jobInfo = infoIterator.next();

                ComponentName componentName = jobInfo.getService();

                if(componentName.getPackageName().equals(packageName)
                        && componentName.getClassName().equals(classN)){
                    jobScheduler.cancel(jobInfo.getId());
                }
            }
        }
    }

    public static void cancelPendingJob(Context context, int jobId) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        if (jobScheduler != null) {
            List<JobInfo> infoList = jobScheduler.getAllPendingJobs();

            Iterator<JobInfo> infoIterator = infoList.iterator();

            while (infoIterator.hasNext()) {
                JobInfo jobInfo = infoIterator.next();

                if(jobInfo.getId()==jobId){
                    jobScheduler.cancel(jobId);
                    break;
                }
            }
        }
    }
}
