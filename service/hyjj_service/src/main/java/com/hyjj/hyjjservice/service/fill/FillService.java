package com.hyjj.hyjjservice.service.fill;

import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataHtml;
import com.hyjj.hyjjservice.controller.fill.viewObject.ReportDataList;
import com.hyjj.hyjjservice.dataobject.ReportTemplate;

import java.util.List;

public interface FillService {
     List<ReportDataList> getReportListByUserId(Long userid);

     ReportDataHtml getReportDataHtml(Integer id,Long userId);

     int saveReportDataHtml(ReportDataHtml reportDataHtml);

     int submitReportData(ReportDataHtml reportDataHtml);

     int clearReportData(Integer reportId);

     ReportTemplate getRowAndColByTemplateId(Integer reportId);

     List<String> getReportNumber(List<Long> reportId);
}
