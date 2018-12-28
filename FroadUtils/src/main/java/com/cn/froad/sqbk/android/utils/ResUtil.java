package com.cn.froad.sqbk.android.utils;

import android.content.Context;

/**
 * @author Created by SimenHi.
 * @date 创建日期 2018/12/13 13:48
 * @modify 修改者 SimenHi
 */
public class ResUtil {

    public static String getString(Context mContext, int resId) {
        return mContext.getString(resId);
    }

    public static String getStrings(Context mContext,int resId, Object... formatArgs) {
        return mContext.getString(resId,formatArgs);
    }

}
