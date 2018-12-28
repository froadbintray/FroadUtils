package com.cn.froad.sqbk.android.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import java.util.List;


/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 13:46
 * @modify 修改者 SimenHi
 */
public class SystemUtil {

    /**
     * 查询指定app是否在前台
     *
     * @param packageName 指定app的包名(一般取当前应用包名,因为Build.VERSION.SDK_INT >= 21此方法对其他应用包名没有效果)
     */
    public static boolean appIsAtTaskTop(Context context, String packageName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= 21) {
            return isTopForLOLLIPOP(manager, packageName);
        } else {
            return isTop(manager, packageName);
        }
    }

    private static boolean isTop(ActivityManager manager, String packageName) {
        /** 获取当前正在运行的任务栈列表， 越是靠近当前运行的任务栈会被排在第一位，之后的以此类推 */
        List<ActivityManager.RunningTaskInfo> runningTasks = manager
                .getRunningTasks(1);
        /** 获得当前最顶端的任务栈，即前台任务栈 */
        ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
        /** 获取前台任务栈的最顶端 Activity */
        ComponentName topActivity = runningTaskInfo.topActivity;
        /** 获取应用的包名并比对 */
        return packageName.equals(topActivity.getPackageName());
    }

    /**
     * 检查某个包是否在前台(Build.VERSION.SDK_INT >= 21时,只能判断当前包)
     */
    private static boolean isTopForLOLLIPOP(ActivityManager manager, String despackageName) {
        List<ActivityManager.RunningAppProcessInfo> processInfos = manager.getRunningAppProcesses();
        if (processInfos == null) return false;
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo
                    .IMPORTANCE_FOREGROUND) {
                if (processInfo.pkgList == null) return false;
                for (String packageName : processInfo.pkgList) {
                    if (despackageName.equals(packageName)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
