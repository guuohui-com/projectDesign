package com.ysu.graduationproject.po;

import java.util.Date;
import java.util.List;

public class Patientcase {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.tableId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String tableid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.patientId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String patientid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.doctorId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String doctorid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.content
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.patientType
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String patienttype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.createTime
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.detailsId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String detailsid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column patientcase.delFlag
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    private String delflag;

    /*
    * 个人病理基本信息列表
    * */
    private List<Basedetail> basedetailList;

    /*
     * 个人病历更多信息列表
     * */
    private List<Moredetails> moredetails;

    public List<Basedetail> getBasedetailList() {
        return basedetailList;
    }

    public void setBasedetailList(List<Basedetail> basedetailList) {
        this.basedetailList = basedetailList;
    }

    public List<Moredetails> getMoredetails() {
        return moredetails;
    }

    public void setMoredetails(List<Moredetails> moredetails) {
        this.moredetails = moredetails;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Patient patient;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.tableId
     *
     * @return the value of patientcase.tableId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.tableId
     *
     * @param tableid the value for patientcase.tableId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setTableid(String tableid) {
        this.tableid = tableid == null ? null : tableid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.patientId
     *
     * @return the value of patientcase.patientId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getPatientid() {
        return patientid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.patientId
     *
     * @param patientid the value for patientcase.patientId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setPatientid(String patientid) {
        this.patientid = patientid == null ? null : patientid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.doctorId
     *
     * @return the value of patientcase.doctorId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getDoctorid() {
        return doctorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.doctorId
     *
     * @param doctorid the value for patientcase.doctorId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid == null ? null : doctorid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.content
     *
     * @return the value of patientcase.content
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.content
     *
     * @param content the value for patientcase.content
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.patientType
     *
     * @return the value of patientcase.patientType
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getPatienttype() {
        return patienttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.patientType
     *
     * @param patienttype the value for patientcase.patientType
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setPatienttype(String patienttype) {
        this.patienttype = patienttype == null ? null : patienttype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.createTime
     *
     * @return the value of patientcase.createTime
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.createTime
     *
     * @param createtime the value for patientcase.createTime
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.detailsId
     *
     * @return the value of patientcase.detailsId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getDetailsid() {
        return detailsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.detailsId
     *
     * @param detailsid the value for patientcase.detailsId
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setDetailsid(String detailsid) {
        this.detailsid = detailsid == null ? null : detailsid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column patientcase.delFlag
     *
     * @return the value of patientcase.delFlag
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column patientcase.delFlag
     *
     * @param delflag the value for patientcase.delFlag
     *
     * @mbg.generated Tue Apr 07 13:18:01 CST 2020
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag == null ? null : delflag.trim();
    }
}