package com.example.demo.service;

import com.example.demo.vo.ExcelExportVO;

import java.util.List;

/**
 * @author yangjianhui
 * @version $Id ExcelExportService.java, v 0.1 2018-06-27 16:46 yangjianhui Exp $$
 */
public interface ExcelExportService {

    List<ExcelExportVO> exportExcel();
}
