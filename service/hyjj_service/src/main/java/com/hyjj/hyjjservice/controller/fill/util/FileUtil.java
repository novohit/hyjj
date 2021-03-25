package com.hyjj.hyjjservice.controller.fill.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class FileUtil {

    @Value("${file.download.url}")
    private String pathName;

    public void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        System.out.println(pathName);
        File file = new File(pathName + filename);
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        int last = (int)file.length() % buff.length;
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(file));
        int i = bis.read(buff);
        while (i > 0) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
        outputStream.write(buff, 0, last);
        outputStream.flush();
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
