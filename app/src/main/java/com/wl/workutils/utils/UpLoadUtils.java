package com.wl.workutils.utils;

import android.app.Activity;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;

/**
 * Created by ${wyh} on 2018/5/9.
 * oss上传 工具类
 */

public class UpLoadUtils {

    private static UpLoadUtils upLoadUtils;

    /**
     * 实例化单例对象
     *
     * @return
     */
    public static UpLoadUtils getInstance() {
        if (upLoadUtils == null) {
            synchronized (UpLoadUtils.class) {
                if (upLoadUtils == null) {
                    upLoadUtils = new UpLoadUtils();
                }
            }
        }
        return upLoadUtils;
    }

    /**
     * 初始化oss
     * @param activity
     * @return
     */
    public OSS initOSS(Activity activity) {
//        OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(stsServer);
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIAEq6rGDO3k4G", "JZ1iVi74kw4f10mLMWmUeVCA0Q0uhQ");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(activity, "oss-cn-beijing.aliyuncs.com", credentialProvider, conf);
        OSSLog.enableLog();
        return oss;
    }
}
