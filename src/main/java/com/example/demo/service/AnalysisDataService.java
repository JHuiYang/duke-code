package com.example.demo.service;

import com.example.demo.vo.DataVO;

import java.util.List;

/**
 * @author yangjianhui
 * @version $Id AnalysisDataService.java, v 0.1 2018-06-27 17:11 yangjianhui Exp $$
 */
public interface AnalysisDataService {

    DataVO queryResultDate(String startTime, String endTime);

}
