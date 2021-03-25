package com.hyjj.hyjjservice.controller.fill.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        File file = new File("C:\\Users\\86137\\Desktop\\" + filename);
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
}
