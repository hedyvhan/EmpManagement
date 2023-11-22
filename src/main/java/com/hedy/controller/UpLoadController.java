package com.hedy.controller;

import com.hedy.Entity.Result;
import com.hedy.utils.QCloud;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UpLoadController {
    /**
     * @description: 本地上传文件
     * @param name:
     * @param age:
     * @param image:
     * @return com.hedy.Entity.Result
     * @author: hedy
     * @date: 2023/10/26 22:05
     */
/*    @PostMapping("/upload")
    public Result upload(String name, Insert age, MultipartFile image) throws IOException {
        log.info("文件上传:{},{},{}", name, age, image);
        //1. 获取文件名
        String fileName = image.getOriginalFilename();

        //2. 构造唯一的文件名（不重复）-uuid(通用唯一识别码)
            //获取文件类型,即.txt
        int index = fileName.lastIndexOf(".");
        String FileType = fileName.substring(index);
        String fileOnlyName = UUID.randomUUID().toString() + FileType;
        log.info("唯一文件名：{}", fileOnlyName);

        //3. 保存到服务器本地
        image.transferTo(new File("../file/" + fileOnlyName ));

        //这里默认上传文件的大小不大于1M,如果要修改限制限制，去配置application.properties里面修改

        return Result.success();
    }*/

    @Autowired
    private QCloud qCloud;

    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile image) throws IOException {
        if (image != null) {
            String url = qCloud.upLoad(image);
            return Result.success(url);
        }
        else return Result.error("文件为空！");
    }
}
