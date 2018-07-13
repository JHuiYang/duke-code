package com.example.demo.dao;

import com.example.demo.vo.DataVO;
import com.example.demo.vo.ExcelExportVO;
import com.example.demo.vo.JingxiMeiVO;
import com.example.demo.vo.MuKuaiVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangjianhui
 * @version $Id ExcelExportDao.java, v 0.1 2018-06-27 16:25 yangjianhui Exp $$
 */
@Mapper
public interface ExcelExportDao {

    @Select("SELECT SHUI_XI_GUI_SHI AS shuiXiGuiShiToTal ,MU_TAN AS muTanTotal ,SHUI_XI_MEI AS shuiXiMeiTotal, MU_KUAI AS muKuaiTotal,LIAO_PI AS batch," +
            "PEI_FANG AS formula,XI_SHU AS coeffcient,PEI_LIAO_REN AS batchingPerson   FROM D_YL_LOADING_STATISTICS")
    List<ExcelExportVO> exportExcel();


    @Select("select sum((case when DB04_03_OUT >= 600 then 1 else 0 end)) as shuiXiGuiShiToTal from T_YUANLIAO_VAL" +
            "  where 'date_time' >= to_date(#{startTime},'yyyy-mm-dd hh24:mi:ss')" +
            "and 'date_time‘ <= to_date(#{endTime},'yyyy-mm-dd hh24:mi:ss')")
    DataVO queryShuiXiGuiShiTotal(@Param("startTime") String  startTime,@Param("endTime")  String endTime);


    @Select("SELECT SUM((case WHEN DB04_07_OUT >= 600 THEN 1 ELSE 0 END)) as mukuaiTotal FROM T_YUANLIAO_VAL WHERE " +
            "'data_time' >= TO_DATE(#{startTime}, 'yyyy-mm-dd hh24:mi:ss') and 'data_time' <= TO_DATE(#{endTime}, 'yyyy-mm-dd hh24:mi:ss')")
    MuKuaiVO queryMuKuaiTotal(@Param("startTime") String  startTime,@Param("endTime")  String endTime);



    @Select("SELECT SUM((case WHEN DB04_06_OUT >= 600 THEN 1 ELSE 0 END)) as mukuaiTotal FROM T_YUANLIAO_VAL WHERE " +
            "'data_time' >= TO_DATE(#{startTime}, 'yyyy-mm-dd hh24:mi:ss') and 'data_time' <= TO_DATE(#{endTime}, 'yyyy-mm-dd hh24:mi:ss')")
    JingxiMeiVO queryJingXiMeiTotal(@Param("startTime") String  startTime, @Param("endTime")  String endTime);

    @Insert("INSERT INTO D_YL_LOADING_STATISTICS(ID,}SHUI_XI_GUI_SHI,MU_TAN,JIAO_YOU,SHUI_XI_MEI,MU_KUAI) " +
            "VALUES(dataId.nextval,#{shuiXiGuiShiToTal},#{muTanTotal},#{youJiaoAndLanTanTotal},#{shuiXiMeiTotal},#{muKuaiTotal})")
    @SelectKey(statement = "select dataId.nextval from dual",keyProperty = "ID",resultType = int.class,before = true)
    int insertData(DataVO dataVO);
}