package com.moncoder.lingo.oss.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.oss.component.OssProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云存储工具类
 * @date 2024/4/15 21:11
 */
@Data
@NoArgsConstructor
public class OssUtil {

    private OSS ossClient;
    @Autowired
    private OssProperties ossProperties;

    @PostConstruct
    public void init() {
        // 创建 OSSClient 实例
        ossClient = new OSSClientBuilder().build(
                ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret()
        );
    }

    /**
     * 文件上传
     *
     * @param bytes      ：传入的文件要转为byte[]
     * @param objectName ：表示在oss中存储的文件名字。
     * @return 文件路径
     */
    public String upload(byte[] bytes, String objectName) {
        // 创建OSSClient实例。
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
            throw new ApiException("文件上传失败！");
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw new ApiException("文件上传失败！");
        }
        // 文件访问路径规则 https://BucketName.Endpoint/ObjectName
        return "https://" + bucketName + "." + endpoint + "/" + objectName;
    }

    @PreDestroy
    public void close() {
        if (ossClient != null) {
            ossClient.shutdown();
        }
    }
}
