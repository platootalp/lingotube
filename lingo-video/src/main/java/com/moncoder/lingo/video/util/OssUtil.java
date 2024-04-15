package com.moncoder.lingo.video.util;

import com.aliyun.oss.*;
import com.moncoder.lingo.video.component.OssProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云存储工具类
 * @date 2024/4/15 21:11
 */
@Component
@Data
@AllArgsConstructor
public class OssUtil {

    @Autowired
    private OSS ossClient;
    @Autowired
    private OssProperties ossProperties;

    /**
     * 文件上传
     *
     * @param bytes      ：传入的文件要转为byte[]
     * @param objectName ：表示在oss中存储的文件名字。
     * @return 文件路径
     */
    public String upload(byte[] bytes, String objectName) {
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String bucketName = ossProperties.getBucketName();
        String endpoint = ossProperties.getEndpoint();
        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException e) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + e.getErrorMessage());
            System.out.println("Error Code:" + e.getErrorCode());
            System.out.println("Request ID:" + e.getRequestId());
            System.out.println("Host ID:" + e.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        return stringBuilder.toString();
    }
}
