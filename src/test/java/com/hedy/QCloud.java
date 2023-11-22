//腾讯云上传测试
package com.hedy;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QCloud.class)
public class QCloud {

    @Value("${spring.tengxun.SecretId}")
    private String secretId;

    @Value("${spring.tengxun.SecretKey}")
    private String secretKey;

    @Value("${spring.tengxun.region}")
    private String region;

    @Value("${spring.tengxun.bucketName}")
    private String bucketName;

    @Value("${spring.tengxun.url}")
    private String path;

    public COSClient initCOSClient(){
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    /**
     * 上传文件
     */
    @Test
    public void upLoad(){
        try {
            String filePath = "C:/Users/han/Desktop/新增许可证示例图.png";
            // 指定要上传的文件
            File localFile = new File(filePath);
            // 指定要上传到 COS 上对象键
            String key = "img/新增许可证示例图.jpg";
            //System.out.println(key + "key----------------------");
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);

            COSClient cosClient1 = initCOSClient();

            PutObjectResult putObjectResult = cosClient1.putObject(putObjectRequest);
            // 设置权限(公开读)
            cosClient1.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            System.out.println("url------------" + path + "/"+ key);
        } catch (CosServiceException serverException) {
            serverException.printStackTrace();
        } catch (CosClientException clientException) {
            clientException.printStackTrace();
        }
    }


/*    *//**
     * 生成文件路径
     *
     * @return
     *//*
    private String getFileKey(String originalfileName) {
        String filePath = "test/";
        //1.获取后缀名 2.去除文件后缀 替换所有特殊字符
        String fileType = originalfileName.substring(originalfileName.lastIndexOf("."));
        String fileStr = StrUtil.removeSuffix(originalfileName, fileType).replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "_");
        filePath +=  new DateTime().toString("yyyyMMddHHmmss") + "_" + fileStr + fileType;
        return filePath;
    }*/

}