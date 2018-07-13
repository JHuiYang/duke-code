package com.example.demo.service.Impl;

import com.example.demo.dao.ExcelExportDao;
import com.example.demo.service.AnalysisDataService;
import com.example.demo.vo.DataVO;
import com.example.demo.vo.JingxiMeiVO;
import com.example.demo.vo.MuKuaiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangjianhui
 * @version $Id AnalysisDataServiceImpl.java, v 0.1 2018-06-27 17:12 yangjianhui Exp $$
 */
@Component
public class AnalysisDataServiceImpl implements AnalysisDataService {

    @Autowired
    private ExcelExportDao excelExportDao;

    @Scheduled(cron = "59 59 7 * * ?")
    public void one(){
        queryResultDate("00:00:00","07:59:59");
    }

    @Scheduled(cron = "59 59 15 * * ?")
    public void two(){
        queryResultDate("08:00:00","15:59:59");
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void three(){
        queryResultDate("16:00:00","23:59:59");
    }

    @Override
    public DataVO queryResultDate(String startTime, String endTime) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm");
        String today = sdf.format(new Date());

        //水硅石
        DataVO dataVO = excelExportDao.queryShuiXiGuiShiTotal(today +" " + startTime, today +" "+ endTime);

        //木块
        MuKuaiVO muKuaiVO =  excelExportDao.queryMuKuaiTotal(today +" " + startTime, today +" "+ endTime);

        //精铣煤
        JingxiMeiVO jingxiMeiVO = excelExportDao.queryJingXiMeiTotal(today +" " + startTime, today +" "+ endTime);

        if (dataVO != null) {
            dataVO.setShuiXiGuiShiToTal(dataVO.getShuiXiGuiShiToTal() * 600);
            dataVO.setMuKuaiTotal(muKuaiVO.getMuKuaiTotal() * 600);
            dataVO.setYouJiaoAndLanTanTotal(jingxiMeiVO.getShuiXiGuishiVO() * 600);
        }

        try {
            excelExportDao.insertData(dataVO);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dataVO;
    }
}