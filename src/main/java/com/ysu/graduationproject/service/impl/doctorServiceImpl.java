package com.ysu.graduationproject.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.dao.*;
import com.ysu.graduationproject.po.*;
import com.ysu.graduationproject.service.IdoctorService;
import com.ysu.graduationproject.utils.GDSessionUtils;
import com.ysu.graduationproject.utils.GDUtils;
import com.ysu.graduationproject.utils.S_SmsDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class doctorServiceImpl implements IdoctorService {
    private String doctorId = "931bd723-0d84-4f3e-b3d2-222a0dadba4c";

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    PatientcaseMapper patientcaseMapper;

    @Autowired
    DetailsMapper detailsMapper;

    @Autowired
    BasedetailMapper basedetailMapper;

    @Autowired
    MoredetailsMapper moredetailsMapper;
    //医生的手机号是否已经注册过
    //存在 true ，没有 false
    public boolean isDoctorAlive(String phone){
        Doctor doctor = doctorMapper.selectByPhone(phone);
        if(GDUtils.isNull(doctor)){
            return false;
        }
        return true;
    }

    //通过patientId找到detailsId
    public String findDetailsId(String patientId){
        return basedetailMapper.findDetailsId(patientId);
    }

    @Override
    public ServerResponse doctorRegister(Doctor doctor) {
        if(GDUtils.isNull(doctor)){
            return ServerResponse.createServerResponseByFail("请填写用户信息");
        }
        if(GDUtils.isNull(doctor.getPhone())){
            return ServerResponse.createServerResponseByFail("请填写电话号码");
        }
        if (isDoctorAlive(doctor.getPhone())) {
            return ServerResponse.createServerResponseByFail("该手机号已经注册过");
        }
        if(GDUtils.isNull(doctor.getPassword())){
            return ServerResponse.createServerResponseByFail("请填写密码");
        }
        doctor.setTableid(UUID.randomUUID().toString().trim());
        int row = doctorMapper.insertSelective(doctor);
        return ServerResponse.createServerResponseBySucces("注册成功",doctor);
    }

    @Override
    public ServerResponse doctorLogin(Doctor doctor) {
        if(GDUtils.isNull(doctor)){
            return ServerResponse.createServerResponseByFail("请填写用户信息");
        }
        if (GDUtils.isNull(doctor.getPhone())){
            return ServerResponse.createServerResponseByFail("请填写手机号");
        }
        if (GDUtils.isNull(doctor.getPassword())){
            return ServerResponse.createServerResponseByFail("请填写密码");
        }
        if(!isDoctorAlive(doctor.getPhone())){
            return ServerResponse.createServerResponseByFail("该手机号尚未注册");
        }
        Doctor doctorCheck = doctorMapper.selectByPhoneAndPassword(doctor);
        if(GDUtils.isNull(doctorCheck)){
            return ServerResponse.createServerResponseByFail("密码错误");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        doctorCheck.setBirthdayS(df.format(doctorCheck.getBirthday()));
        return ServerResponse.createServerResponseBySucces("登陆成功",doctorCheck);
    }

    @Override
    public ServerResponse updateNumb(Doctor doctor, HttpSession session) {
        doctor.setTableid(GDSessionUtils.getDoctorSession(session).getTableid());
        int row = doctorMapper.updateByPrimaryKeySelective(doctor);
        if(row<1){
            return ServerResponse.createServerResponseByFail("更新失败");
        }
        return ServerResponse.createServerResponseBySucces("更新成功");
    }

    @Override
    public ServerResponse selectPatient(HttpSession session) {
        String doctorId = GDSessionUtils.getDoctorSession(session).getTableid();
        List<Patient> patientList = patientMapper.selectPatientByDoctorId(doctorId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        for (Patient p:patientList) {
            p.setBirthdayS(df.format(p.getBirthday()));
            p.setBasedetail(basedetailMapper.selectRecentData(p.getTableid()));
        }
        if(GDUtils.isNull(patientList)){
            return ServerResponse.createServerResponseByFail("您还没有患者");
        }
        return ServerResponse.createServerResponseBySucces("查询成功",patientList);
    }

    //医生查看单个病人的病例
    @Override
    public ServerResponse selectPatientCase(Patient patient) {
        System.out.println("======================="+patient.getTableid());
        if(GDUtils.isNull(patient)){
            return ServerResponse.createServerResponseByFail("请填写信息");
        }
        if(GDUtils.isNull(patient.getTableid())){
            return ServerResponse.createServerResponseByFail("请选择患者");
        }
        //病例编号信息
        Patientcase patientcase = patientcaseMapper.selectByPatientId(patient.getTableid());
        System.out.println("===========patientcase==============="+patientcase.getTableid());
        if(GDUtils.isNull(patientcase)){
            return ServerResponse.createServerResponseByFail("您还没有病例");
        }
        Details details = detailsMapper.selectByPrimaryKey(patientcase.getDetailsid());
        patientcase.setDetails(details);
        //个人信息
        Patient patientTest = patientMapper.selectByPrimaryKey(patient.getTableid());
        //日常数据信息
        List<Basedetail> basedetailList = basedetailMapper.selectByDetailsId(patientcase.getDetailsid());
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");
        for (Basedetail b :basedetailList) {
           b.setCreatetimeS(df.format(b.getCreatetime()).toString().trim());
        }
        //高级数据信息
        List<Moredetails> moredetailsList = moredetailsMapper.selectByDetailsId(patientcase.getDetailsid());
        patientcase.setPatient(patientTest);
        patientcase.setBasedetailList(basedetailList);
        patientcase.setMoredetails(moredetailsList);
        return ServerResponse.createServerResponseBySucces("查询成功",patientcase);
    }

    @Override
    public ServerResponse updatePatientType(Patient patient) {
        if(GDUtils.isNull(patient)){
            return ServerResponse.createServerResponseByFail("请填写糖尿病类型信息");
        }
        int row = patientMapper.updateByPrimaryKeySelective(patient);
        if(row<1){
            return ServerResponse.createServerResponseByFail("更新失败");
        }
        return ServerResponse.createServerResponseBySucces("更新成功",patient);
    }

    @Override
    public ServerResponse notifyPatient(Patient patient) {
        //发短信
        SendSmsResponse response = null;
        try {
            response = S_SmsDemo.sendSms(patient.getName(),patient.getPhone());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = null;
            try {
                querySendDetailsResponse = S_SmsDemo.querySendDetails(response.getBizId(),patient.getPhone());
            } catch (ClientException e) {
                e.printStackTrace();
            }
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }
        if(response.getMessage().equals("OK")){
            return ServerResponse.createServerResponseBySucces(null,"短信发送成功");
        }
        return ServerResponse.createServerResponseByFail("短信发送失败");
    }

    @Override
    public ServerResponse inputMoreDetail(MoreDetailsVO moreDetailsVO) {
        if(GDUtils.isNull(moreDetailsVO)){
            return ServerResponse.createServerResponseByFail("请填数据");
        }
        Moredetails moredetails = new Moredetails();
        moredetails = moreDetailsVO.getMoredetails();
        //找到DetailId
        System.out.println("==============findDetailsId(patientId)============="+moreDetailsVO);
        System.out.println("==============findDetailsId(patientId)============="+moreDetailsVO.getTableId());
        System.out.println("==============findDetailsId(patientId)============="+moreDetailsVO.getMoredetails());
        moredetails.setDetailsid(findDetailsId(moreDetailsVO.getTableId()));
        moredetails.setTableid(UUID.randomUUID().toString());
        int row = moredetailsMapper.insertSelective(moredetails);
        if(row<1){
            return ServerResponse.createServerResponseByFail("更新失败");
        }
        return  ServerResponse.createServerResponseBySucces("更新成功",moredetails);
    }

    @Override
    public ServerResponse updateCriticalDetail(Details details) {
        if(GDUtils.isNull(details)){
            return ServerResponse.createServerResponseByFail("请填写患者临界信息");
        }
       int row =  detailsMapper.updateByPrimaryKeySelective(details);
        if(row<1){
            return ServerResponse.createServerResponseByFail("更新失败");
        }
        return ServerResponse.createServerResponseBySucces("更新成功",details);
    }
    @Override
    public ServerResponse showPatientTend(Patient patient) {
        return null;
    }
}
