package com.hedy.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Slf4j
@Component
public class QCloud {

/*    @Value("${spring.tengxun.SecretId}")
    private String secretId;

    @Value("${spring.tengxun.SecretKey}")
    private String secretKey;

    @Value("${spring.tengxun.region}")
    private String region;

    @Value("${spring.tengxun.bucketName}")
    private String bucketName;

    @Value("${spring.tengxun.url}")
    private String path;*/

    @Autowired
    private QCloudProperties qCloudProperties;

   /* public COSClient initCOSClient() {

        log.info("输出："+ secretId + secretKey + region + bucketName+ path);
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }*/

    public String upLoad(MultipartFile image) throws IOException{

        //  获取腾讯云的参数
        String secretId = qCloudProperties.getSecretId();
        String secretKey = qCloudProperties.getSecretKey();
        String region = qCloudProperties.getRegion();
        String bucketName = qCloudProperties.getBucketName();
        String path = qCloudProperties.getUrl();

        // 初始化
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region Region = new Region(region);
        ClientConfig clientConfig = new ClientConfig(Region);
        // 生成 cos 客户端
        COSClient cosClient = new COSClient(cred, clientConfig);



        // 准备工作
        String cloudUrl = "";
        try {
            //1. 获取文件名

            log.info("文件上传服务器，文件名：{}", image.getOriginalFilename());

            // 指定要上传到 COS 上对象键， 通过构造唯一的文件名（不重复）-uuid(通用唯一识别码)


            // 通过流式上传，需要参数key和文件流
            // 1. 获取key， key = 唯一名称+后缀，唯一名称使用uuid

            //获取文件名称
            String fileName = image.getOriginalFilename();
            //获取文件类型,即.txt
            int index = fileName.lastIndexOf(".");
            String FileType = fileName.substring(index);
            //uuid文件名+文件后缀
            String fileOnlyName = UUID.randomUUID().toString() + FileType;
            log.info("保存在云端的文件名：{}", fileOnlyName);
            String key = "img/"+ fileOnlyName;
            log.info("key：{}", key);


            //COSClient cosClient = initCOSClient();


            //流式上传
            cosClient.putObject(bucketName, key, image.getInputStream(), null);


            // 设置权限(公开读)
            cosClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);

            cloudUrl = "url------------" + path + "/" + key;
            log.info(cloudUrl);

        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cloudUrl;
    }

}
