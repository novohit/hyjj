package com.hyjj.hyjjservice.service.fill;

import com.hyjj.hyjjservice.dataobject.ReportDataHtml;
import com.hyjj.hyjjservice.dataobject.ReportDataList;

import java.util.List;

public interface FillService {
     List<ReportDataList> getReportListByUserId(Long userid);

     ReportDataHtml getReportDataHtml(Integer id,Long userId);

     int saveReportDataHtml(ReportDataHtml reportDataHtml);

     int submitReportData(ReportDataHtml reportDataHtml);
}
