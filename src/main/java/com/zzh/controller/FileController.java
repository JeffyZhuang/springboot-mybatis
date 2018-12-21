package com.zzh.controller;

import com.zzh.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * @Author: zzh
 * @Description:文件的上传和下载
 * @Date: 2018/12/17
 */
@RestController
@RequestMapping(value = "/zzh/file")
@Slf4j
public class FileController {

    @RequestMapping("/download")
    public ApiResult downloadFile(HttpServletRequest request, HttpServletResponse response) throws
            UnsupportedEncodingException {
        String fileName = "输入输出DEMO.doc";
        if (fileName != null) {
            //设置文件路径
            //String realPath = "file/";
            response.setCharacterEncoding("utf-8");
            File file = new File(getClass().getResource("/").getPath(), fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(),
                        "ISO8859-1"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ApiResult.success();
    }
}
