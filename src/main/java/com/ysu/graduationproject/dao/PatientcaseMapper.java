package com.ysu.graduationproject.dao;

import com.ysu.graduationproject.po.Patientcase;

public interface PatientcaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int deleteByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insert(Patientcase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insertSelective(Patientcase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    Patientcase selectByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKeySelective(Patientcase record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table patientcase
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKey(Patientcase record);

    Patientcase selectByPatientId(String patientId);
}