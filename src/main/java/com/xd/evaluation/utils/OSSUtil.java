package com.xd.evaluation.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.xd.evaluation.config.AliOSSConfig;
import com.xd.evaluation.exception.EvaluationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云，对象存储工具类
 * Created By Cx On 2018/12/29 15:34
 */
@Component
@Slf4j
public class OSSUtil {

    @Autowired
    AliOSSConfig aliOSSConfig;

    //上传文件到阿里云OSS，返回文件访问url
    public String upload(MultipartFile file) {
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        // 链接地址是：
        // https://help.aliyun.com/document_detail/oss/sdk/java-sdk/init.html?spm=5176.docoss/sdk/java-sdk/get-start
        OSSClient ossClient = new OSSClient(aliOSSConfig.getEndpoint(), aliOSSConfig.getAccessKeyId()
                , aliOSSConfig.getAccessKeySecret());
        try {
            String bucketName = aliOSSConfig.getBucketName();
            // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
            // 链接地址是：
            // https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
            if (!ossClient.doesBucketExist(bucketName)) {
                // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
                // 链接地址是：
                // https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
                ossClient.createBucket(bucketName);
            }
            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            //生成一个唯一文件名，防止文件名相同导致文件被覆盖
            String fileName = file.getOriginalFilename();
            fileName = System.currentTimeMillis() + "$" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            ossClient.putObject(bucketName, fileName,file.getInputStream());
            //如果是file，可写为：ossClient.putObject(bucketName, fileName,file);
            log.info("Object：" + fileName + "存入OSS成功。");
            return bucketName + "." + aliOSSConfig.getEndpoint() + "/" + fileName;
        } catch (Exception oe) {
            log.error("上传文件失败，e = {}",oe.getMessage());
            throw new EvaluationException("上传文件失败");
        }finally {
            ossClient.shutdown();
        }
    }
}
