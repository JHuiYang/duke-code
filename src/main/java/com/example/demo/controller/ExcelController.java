package com.example.demo.controller;

import com.example.demo.service.ExcelExportService;
import com.example.demo.vo.ExcelExportVO;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelExportService excelExportService;

    //创建表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
//        font.setBold(true);
        font.setItalic(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("水洗硅石（kg）");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("木炭（kg）");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("油焦（kg）");
        cell.setCellStyle(style);


        cell = row.createCell(3);
        cell.setCellValue("水洗煤（kg）");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("木块（kg）");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("料批");
        cell.setCellStyle(style);


        cell = row.createCell(6);
        cell.setCellValue("配方");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("系数");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("配料人");
        cell.setCellStyle(style);

    }

    //生成user表excel
    @GetMapping(value = "/exportExcel")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        List<ExcelExportVO> excelExportVOS = excelExportService.exportExcel();
        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(ExcelExportVO vo : excelExportVOS){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(vo.getShuiXiGuiShiToTal());
            row.createCell(1).setCellValue(vo.getMuTanTotal());
            row.createCell(2).setCellValue(vo.getYouJiaoAndLanTanTotal());
            row.createCell(3).setCellValue(vo.getShuiXiMeiTotal());
            row.createCell(4).setCellValue(vo.getMuKuaiTotal());
            row.createCell(5).setCellValue(vo.getBatch());
            row.createCell(6).setCellValue(vo.getFormula());
            row.createCell(7).setCellValue(vo.getCoeffcient());
            row.createCell(8).setCellValue(vo.getBatchingPerson());
            rowNum++;
        }

        String fileName = "上料统计.xls";


        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
