package com.ysu.graduationproject.dao;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Doctor;

import java.util.List;

public interface DoctorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int deleteByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insert(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int insertSelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    Doctor selectByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKeySelective(Doctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table doctor
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    int updateByPrimaryKey(Doctor record);

    Doctor selectByPhone(String phone);

    Doctor selectByPhoneAndPassword(Doctor doctor);

    List<Doctor> selectAll();

}