package com.manage.controller;

import com.manage.common.ResponseMO;
import com.manage.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description:文件操作类
 * Author:kbq
 * Date: 2019-12-24 18:26
 */
@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件操作控制类")
public class FileController extends BaseController {



    @PostMapping("/upload")
    @ApiOperation(value = "上传文件")
    public ResponseMO uploadFile(String filePath, @RequestParam("file") MultipartFile file) {
        List imgTypes = Stream.of("gif", "jpg", "png","jpeg").collect(Collectors.toList());
        String originName = file.getOriginalFilename();
        String fileSuffix = originName.substring(originName.lastIndexOf(".") + 1).toLowerCase();
        if (!imgTypes.contains(fileSuffix)) {
            return error("文件格式不正确");
        }
        //返回当前项目的工作目录
        String dirPath = System.getProperty("user.home");
        String path = "";
        if (StringUtils.isEmpty(filePath)){
            String newFileName = StringUtil.generateUUID() + originName;
            path = File.separator + "uploadImage" + File.separator + newFileName;
        }else {
            path = filePath;
        }
        System.err.println(path.substring(13));
        File storeFile = new File(dirPath + path);
        if (!storeFile.getParentFile().exists()) {
            storeFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(storeFile);
            if (StringUtils.isEmpty(filePath)){
            }
            return success(path);
        } catch (IOException e) {
            log.info("文件上传异常=[{}]", e);
            return error("文件上传异常");
        }
    }

    @DeleteMapping("/delete/{fileName}")
    @ApiOperation(value = "删除文件")
    public ResponseMO deleteFile(@PathVariable("fileName") String fileName) {
        String dirPath = System.getProperty("user.home");
        String path = File.separator + "uploadImage" + File.separator + fileName;
        File deleteFile = new File(dirPath + path);
        if (!deleteFile.exists()) {
            return error("您要删除的文件不存在");
        }
        deleteFile.delete();
        return success("文件删除成功");
    }

    @GetMapping("/download")
    @ApiOperation(value = "下载文件")
    public ResponseMO downloadFile() {
        return success("文件下载成功");
    }




}
