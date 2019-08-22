package com.wl.workutils.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${wyh} on 2018/1/5.
 */

public class MD5Utils {

    private static StringBuffer buffer;

    public static String md5(String inputStr) throws NoSuchAlgorithmException {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(inputStr.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String sign(Map<String,String> map){
        if(map!=null&&map.size()>0) {
            buffer = new StringBuffer();
            Set<String> set = map.keySet();
            Iterator<String> keyIter = set.iterator();
            String key;
            String value;
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = map.get(key);
                buffer.append(key).append("=").append(value).append("&");
            }

        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }
}
