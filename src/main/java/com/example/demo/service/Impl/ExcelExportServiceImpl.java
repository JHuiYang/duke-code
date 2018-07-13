package com.example.demo.service.Impl;

import com.example.demo.dao.ExcelExportDao;
import com.example.demo.service.ExcelExportService;
import com.example.demo.vo.ExcelExportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangjianhui
 * @version $Id ExcelExportServiceImpl.java, v 0.1 2018-06-27 16:47 yangjianhui Exp $$
 */
@Service
public class ExcelExportServiceImpl  implements ExcelExportService{

    @Autowired
    private ExcelExportDao excelExportDao;

    @Override
    public List<ExcelExportVO> exportExcel() {
        return excelExportDao.exportExcel();
    }
}