package com.hyjj.hyjjservice.controller.fill.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Component
public class FileUtil {

    @Value("${file.download.url}")
    private String pathName;

    public void download(String filename, HttpServletResponse res) {
        try {
            filename = filename + ".xlsx";
            System.out.println(filename);
            Workbook workbook = new XSSFWorkbook(pathName + filename);
            System.out.println(pathName);
            String fileName = URLEncoder.encode(filename, "utf-8");
            res.setContentType("application/octet-stream");
            res.setHeader("content-disposition", "attachment;filename=" + fileName);
            res.setHeader("filename", filename);
            workbook.write(res.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}
