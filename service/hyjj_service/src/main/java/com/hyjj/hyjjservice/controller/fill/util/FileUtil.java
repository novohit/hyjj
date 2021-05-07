package com.hyjj.hyjjservice.controller.fill.util;

import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static Cell getExcelValue(XSSFWorkbook xssfWorkbook,Integer rowNum, Integer cellNum, InputStream is) throws IOException {
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(rowNum);
        return row.getCell(cellNum);
    }

    public static Object getCellValue(Cell cell) {
        String cellValue = "";
        double cellNum;
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            // 数字
            case NUMERIC:
                //日期
                if (DateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = df.format(date);
                    break;
                }else{
                    //数字
                    cellNum = cell.getNumericCellValue();
                    return cellNum;
                }
            // 字符串
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            // 空值
            case BLANK:
                cellValue = "";
                break;
            // 故障
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                break;
        }
        return cellValue;
    }

    public List<Object> getCellList(ReportTemplate reportTemplate,InputStream is) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

        List<Object> cells = new ArrayList<>();
        int i = 0;
        String[] rowStringArray = reportTemplate.getRow().split(",");
        String[] colStringArray = reportTemplate.getCol().split(",");
        int[] rowArray = new int[rowStringArray.length];
        int[] colArray = new int[colStringArray.length];
        for (String s : colStringArray) {
            colArray[i] = Integer.parseInt(s);
            i++;
        }
        i = 0;
        for (String s : rowStringArray) {
            rowArray[i] = Integer.parseInt(s);
            i++;
        }

        for (int j = 0; j < rowArray.length; j++) {
            Cell excelValue = getExcelValue(xssfWorkbook,rowArray[j], colArray[j], is);
            cells.add(getCellValue(excelValue));
        }
        return cells;
    }
}
