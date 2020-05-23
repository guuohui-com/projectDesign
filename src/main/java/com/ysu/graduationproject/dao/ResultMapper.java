package com.ysu.graduationproject.dao;

import com.ysu.graduationproject.po.Result;

import java.util.List;

public interface ResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    int deleteByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    int insert(Result record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    int insertSelective(Result record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    Result selectByPrimaryKey(String tableid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    int updateByPrimaryKeySelective(Result record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Tue Apr 07 13:19:28 CST 2020
     */
    int updateByPrimaryKey(Result record);

    List<Result> selectResultAll(String patientId);
}