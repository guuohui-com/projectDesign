package com.ysu.graduationproject.dao;

import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Patient;

import java.util.List;

public interface BasedetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int deleteByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insert(Basedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insertSelective(Basedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    Basedetail selectByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKeySelective(Basedetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table basedetail
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKey(Basedetail record);

    String findDetailsId(String patientId);

    List<Basedetail> selectByDetailsId(String detailsid);

    Basedetail patientSearchTodayResult();

    int updateByCreateTime(Basedetail basedetail);

    Basedetail selectRecentData(String tableid);
}