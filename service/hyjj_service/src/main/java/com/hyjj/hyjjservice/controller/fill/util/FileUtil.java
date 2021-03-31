package com.hyjj.hyjjservice.controller.fill.util;

import com.hyjj.hyjjservice.dataobject.ReportTemplate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        XSSFCell cell = row.getCell(cellNum);
        return cell;

    }

    public static Object getCellValue(Cell cell) {
        String cellValue = "";
        double cellNum = 0;
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                cellNum = cell.getNumericCellValue();
                return cellNum;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
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
        System.out.println(cells);
        return cells;
    }


}
