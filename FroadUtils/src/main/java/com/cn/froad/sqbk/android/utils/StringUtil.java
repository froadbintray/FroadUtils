package com.cn.froad.sqbk.android.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author Created by Simen.
 * @date 创建日期 2018/12/13 11:46
 * @modify 修改者 Simen
 */
public class StringUtil {

    /**
     * 移除最后指定字符
     *
     * @param src 源字符串，如："abcdefp:"
     * @param cs  指定要移除字符，如：':','p'
     * @return 如："abcdef"
     * @version Jan 21, 2013
     */
    public static String removeLastChar(String src, char... cs) {
        if (src == null || cs == null || cs.length < 1) {
            return src;
        }
        String regex = getMatchLastCharRegex(false, cs);
        return src.replaceAll(regex, "");
    }

    /**
     * 获取匹配最后字符的正则表达式
     *
     * @param isOne 控制是否只匹配最后一个（true），还是匹配最后所有（false）
     * @param cs    匹配字符
     * @return 正则表达式
     * @author fred.fu@magic-point.com
     * @version Jan 21, 2013
     */
    private static String getMatchLastCharRegex(boolean isOne, char... cs) {
        return "[" + String.valueOf(cs) + "]" + (isOne == true ? "" : "+")
                + "$";
    }

    /**
     * 添加后缀
     *
     * @param src    如：abc
     * @param suffix 如："："
     * @return 如：abc：
     * @version Jan 21, 2013
     */
    public static String addSuffix(String src, String suffix) {
        if (src == null || suffix == null) {
            return src;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(src);
        sb.append(suffix);
        return sb.toString();
    }

    /**
     * 字符串末尾冒号转换（添加）：英文->中文冒号
     *
     * @param strColon
     * @return
     * @version Jan 21, 2013
     */
    public static String convertCnColon(CharSequence strColon) {
        return StringUtil.formatColon(strColon, true);
    }

    /**
     * 格式化冒号
     *
     * @param strColon
     * @param isCn     中文冒号（true），英文冒号（false）
     * @return
     * @author fred.fu@magic-point.com
     * @version Jan 21, 2013
     */
    public static String formatColon(CharSequence strColon, boolean isCn) {

        if (strColon == null || strColon.toString().trim().length() < 1) {
            return "";
        }

        return StringUtil.addSuffix(
                StringUtil.removeLastChar(strColon.toString(),
                        StringUtil.getColon(true), StringUtil.getColon(false)),
                String.valueOf(StringUtil.getColon(isCn)));
    }

    /**
     * @param isCn 中文冒号（true），英文冒号（false）
     * @return
     * @author fred.fu@magic-point.com
     * @version Jan 21, 2013
     */
    public static char getColon(boolean isCn) {
        return isCn == true ? '：' : ':';
    }

    /**
     * Check whether the specified string is empty after trim.
     *
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * Check whether the specified string is empty.
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNullOrEmpty(Object o) {
        return o == null || (o instanceof String && isTrimEmpty((String) o));
    }

    public static String toNotNullString(String str) {
        return isNullOrEmpty(str) ? "" : str;
    }


    public static String getValueStartingWith(final String sStr,
                                              final String sPtt, final String sSep, final String sDft) {
        if (isNullOrEmpty(sStr)) {
            return sDft;
        }

        String ret = sDft;
        try {
            int pos = sStr.indexOf(sPtt);
            if (pos < 0) {
                pos = sStr.toUpperCase().indexOf(sPtt.toUpperCase());
            }
            if (pos >= 0) {
                pos += sPtt.length();

                int end = sStr.indexOf(sSep, pos);
                if (end >= pos) {
                    ret = sStr.substring(pos, end);
                } else {
                    ret = sStr.substring(pos);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return ret;
    }

    /**
     * Extracts one parameter from a string. Example:
     * getParseInt("name=Jazz;age=17;active=1", "name", "Frank Bith") => "Jazz"
     *
     * @param line         String containing any number of name=value pairs.
     * @param keyword      Keyword marking the value to extract.
     * @param defaultValue Value used in case the parameter is wrong/not found.
     * @return Extracted value.
     */
    public static String getParseValue(String line, String keyword,
                                       String defaultValue) {
        return getParseValue(line, keyword, defaultValue, "=", ";");
    }

    /**
     * Get the parseValue between seperate tag and end tag.
     *
     * @param line
     * @param keyword
     * @param defaultValue
     * @param seperateTag
     * @param endTag
     * @return
     */
    public static String getParseValue(String line, String keyword, String defaultValue, String seperateTag, String endTag) {
        String ret = defaultValue;
        if (!keyword.endsWith(seperateTag)) {
            keyword += seperateTag;
        }
        String val = getValueStartingWith(line, keyword, endTag, "");
        if (!isNullOrEmpty(val)) {
            ret = val;
        }
        return ret;
    }

    /**
     * Get uri QUERY PARAMETER VALUE.
     *
     * @param line
     * @param keyword
     * @param defaultValue
     * @return
     */
    public static String getURIQueryValue(String line, String keyword, String defaultValue) {
        return getParseValue(line, keyword, defaultValue, "=", "&");
    }

    /**
     * Get uri QUERY PARAMETER VALUE for request. It will split the key & value with form "key="value"&"
     *
     * @param line
     * @param keyword
     * @param defaultValue
     * @return
     */
    public static String getURIQueryValueForRequest(String line, String keyword, String defaultValue) {
        return getParseValue(line, keyword, defaultValue, "=", "\"&");
    }

    /**
     * Appends a semicolon-separated "keyword=value" token to the parameters
     * string.
     *
     * @param sb        String buffer to use.
     * @param parameter The parameter name, does not have to end with "=".
     * @param value     The value of the parameter.
     */
    public static void appendParameter(StringBuffer sb, String parameter,
                                       String value) {
        if (parameter == null) {
            return;
        }

        if (sb.length() > 0) {
            sb.append(";");
        }
        sb.append(parameter);
        if (!parameter.endsWith("=")) {
            sb.append("=");
        }
        if (value != null) {
            sb.append(value);
        }
    }

    /**
     * Check whether the string is numeric.
     *
     * @param string
     * @return
     */
    public static boolean isNumeric(String string) {
        return string.matches("^[-+]?\\d+(\\.\\d+)?$");
    }


    /**
     * parse the url and return the domain name
     *
     * @param url
     * @return
     */
    public static String parseHost(String url) {
        String[] hosts = url.split("/{1,2}");
        if (hosts == null) {
            return "";
        }
        return hosts[0] + "//" + hosts[1];
    }

    /**
     * Hex编码字符串转换为byte数组
     *
     * @param bs
     * @return
     */
    public static byte[] hexString2ByteArray(String bs) {
        int bsLength = bs.length();
        if (bsLength % 2 != 0) {
            return null;
        }
        byte[] cs = new byte[bsLength / 2];
        String st = "";
        for (int i = 0; i < bsLength; i = i + 2) {
            st = bs.substring(i, i + 2);
            cs[i / 2] = (byte) Integer.parseInt(st, 16);
        }
        return cs;
    }

    /**
     * byte数组转换为Hex字符串
     */
    public static String bytes2HexStr(byte[] b) {
        if (b == null) {
            return "";
        }
        String rs = "";
        int bl = b.length;
        byte bt;
        String bts = "";
        int btsl;
        for (int i = 0; i < bl; i++) {
            bt = b[i];
            bts = Integer.toHexString(bt);
            btsl = bts.length();
            if (btsl > 2) {
                bts = bts.substring(btsl - 2).toUpperCase();
            } else if (btsl == 1) {
                bts = "0" + bts.toUpperCase();
            } else {
                bts = bts.toUpperCase();
            }
            // System.out.println("i::"+i+">>>bts::"+bts);
            rs += bts;
        }
        return rs;
    }

    /**
     * byte数组转为Hex字符串，带0x
     *
     * @param b
     * @return
     */
    public static String bytes2Hex0xStr(byte[] b) {
        String rs = "";
        int bl = b.length;
        byte bt;
        String bts = "";
        int btsl;
        for (int i = 0; i < bl; i++) {
            bt = b[i];
            bts = Integer.toHexString(bt);
            btsl = bts.length();
            if (btsl > 2) {
                bts = "0x" + bts.substring(btsl - 2);
            } else if (btsl == 1) {
                bts = "0x0" + bts;
            } else {
                bts = "0x" + bts;
            }
            // System.out.println("i::"+i+">>>bts::"+bts);
            rs += bts + " ";
        }
        // System.out.println("rs::"+rs);
        return rs;
    }

    /**
     * 将16进制字符串转换为常规字符串
     *
     * @param s 16进制字符串
     * @param s 编码
     * @return
     */
    public static String hexStr2String(String s, String encodeType) {
        try {
            return new String(hexString2ByteArray(s), encodeType);
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换为16进制格式字符串
     *
     * @param s
     * @param enCode
     * @return
     */
    public static String string2HexStr(String s, String enCode) {
        if (s == null) {
            return null;
        }
        try {
            byte[] bs = s.getBytes(enCode);
            return bytes2HexStr(bs);
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }
        return null;
    }

    /**
     * int转为Hex字符串
     *
     * @param i
     * @return
     */
    public static String int2HexStr(int i) {
        i = i & 0xFF;
        String si = Integer.toHexString(i);
        if (si.length() < 2) {
            si = "0" + si;
        }
        si = si.toUpperCase();
        return si;
    }

}
