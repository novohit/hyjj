package com.hyjj.hyjjservice.service.statistic.impl.template;

import com.hyjj.hyjjservice.dao.ReportTemplateMapper;
import com.hyjj.hyjjservice.dao.TargetKeyValueMapper;
import com.hyjj.hyjjservice.dao.TargetValueMapper;
import com.hyjj.hyjjservice.dataobject.StatisticsTargetKey;
import com.hyjj.hyjjservice.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板id为1的报表的模板（海水利用项目情况）
 */
@Component
public class TemplateId1 extends AbstractTargetTemplate {
    @Autowired
    private TargetKeyValueMapper targetKeyValueMapper;

    @Autowired
    private TargetValueMapper targetValueMapper;

    @Autowired
    private StatisticService statisticService;


    @Override
    public Integer changeValue(Long reportDataId, List<Double> data) {
        data.remove(4);
        List<StatisticsTargetKey> statisticTargetKey = statisticService.getStatisticTargetKey(141L);
        return addTargetValue(targetValueMapper, targetKeyValueMapper, reportDataId, data, statisticTargetKey);
    }

    public static void main(String[] args) {
        List<String> list = getAllFile("D:\\WeChat Files\\wxid_173poa14wlvf22\\FileStorage\\File\\2021-08\\新建文件夹 (2)", false);
        list.forEach(s -> {
            String[] strings = readFileByChars(s).split("\\$");
            System.out.println(s.split("\\\\")[6]);
        });
    }

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public static String readFileByChars(String fileName) {
        String str = "";

        File file = new File(fileName);

        try {

            FileInputStream in = new FileInputStream(file);

            // size  为字串的长度 ，这里一次性读完

            int size = in.available();

            byte[] buffer = new byte[size];

            in.read(buffer);

            in.close();

            str = new String(buffer, "utf-8");

        } catch (IOException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return str;
    }
}
