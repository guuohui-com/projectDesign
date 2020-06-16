package com.ysu.graduationproject.service.impl;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.dao.*;
import com.ysu.graduationproject.po.*;
import com.ysu.graduationproject.service.IdoctorService;
import com.ysu.graduationproject.service.IpatientService;
import com.ysu.graduationproject.utils.GDSessionUtils;
import com.ysu.graduationproject.utils.GDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements IpatientService {

//    private String patientId = "05ffb4a1-0469-4b19-bf5b-ef3fd2c7cd60";
    private String patientId = "05ffb4a1-0469-4b19-bf5b-ef3fd2c7cd60";


    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private BasedetailMapper basedetailMapper;

    @Autowired
    private PatientcaseMapper  patientcaseMapper;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private doctorServiceImpl doctorService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    //判断该手机号是否已经注册过
    public boolean isUserAlive(String phone){
        Patient patient = patientMapper.selectByPhone(phone);
        if(GDUtils.isNull(patient)){
            return false;//改手机号在数据库中没有
        }
        return true;
    }

    //患者的注册函数
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ServerResponse patientRegister(Patient patient) throws Exception {
        //判断 name，phone，password,birthday是否为空
        if(GDUtils.isNull(patient)){
            return ServerResponse.createServerResponseByFail("信息不能为空");
        }
        if(GDUtils.isNull(patient.getName())){
            return ServerResponse.createServerResponseByFail("请填写姓名");
        }
        if(GDUtils.isNull(patient.getPhone())){
            return ServerResponse.createServerResponseByFail("请填写手机号");
        }
        if(GDUtils.isNull(patient.getPassword())){
            return ServerResponse.createServerResponseByFail("密码不能为空");
        }
        System.out.println(patient.getPassword());
        if(GDUtils.isNull(patient.getBirthday())){
            return ServerResponse.createServerResponseByFail("请填写出生日期");
        }
        boolean isAlive = isUserAlive(patient.getPhone());
        if(isAlive==true){
            return ServerResponse.createServerResponseByFail("该手机号已经注册过");
        }
        patient.setTableid(UUID.randomUUID().toString().trim());
        int row = patientMapper.insertSelective(patient);
        if(row<1){
            throw new Exception("注册失败");
        }
        //注册时生成病例
        Patientcase caseExample = new Patientcase();
        caseExample.setTableid(UUID.randomUUID().toString().trim());
        caseExample.setPatientid(patient.getTableid());
        caseExample.setDetailsid(UUID.randomUUID().toString().trim());
        int caseRow = patientcaseMapper.insertSelective(caseExample);
        if(caseRow<1){
            throw new Exception("注册失败");
        }
        //注册时生成details
        Details details = new Details();
        details.setTableid(UUID.randomUUID().toString().trim());
        details.setCaseid(caseExample.getTableid());
        int detailRow = detailsMapper.insertSelective(details);
        if(caseRow<1){
            throw new Exception("注册失败");
        }
        return ServerResponse.createServerResponseBySucces("注册成功",patient);
    }

    //患者登录函数
    @Override
    public ServerResponse patientLogin(Patient patient) {
        //判断 name，phone，password,birthday是否为空
        if(GDUtils.isNull(patient)){
            return ServerResponse.createServerResponseByFail("信息不能为空");
        }
        if(GDUtils.isNull(patient.getPhone())){
            return ServerResponse.createServerResponseByFail("请填写手机号");
        }
        if(GDUtils.isNull(patient.getPassword())){
            return ServerResponse.createServerResponseByFail("密码不能为空");
        }
        if (!isUserAlive(patient.getPhone())){
            return ServerResponse.createServerResponseByFail("用户不存在");
        }
        Patient checkPatient = patientMapper.selectByPhoneAndPassword(patient);
        if(GDUtils.isNull(checkPatient)){
            return ServerResponse.createServerResponseByFail("密码错误");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(!GDUtils.isNull(checkPatient.getBirthday())){
            checkPatient.setBirthdayS(df.format(checkPatient.getBirthday()));
        }
        return ServerResponse.createServerResponseBySucces("登陆成功",checkPatient);
    }

    @Override
    public ServerResponse patientSelectDoctor() {
        List<Doctor> doctorList = doctorMapper.selectAll();
        if(GDUtils.isNull(doctorList)){
            return ServerResponse.createServerResponseByFail("没有医生");
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        for (Doctor doctor: doctorList) {
            doctor.setBirthdayS(df.format(doctor.getBirthday()));
        }
        return ServerResponse.createServerResponseBySucces("查询成功",doctorList);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public ServerResponse patientChooseDoctor(Doctor doctor,HttpSession session) throws Exception {
        if(GDUtils.isNull(doctor)){
            return ServerResponse.createServerResponseByFail("请选择患者");
        }
        if(GDUtils.isNull(doctor.getTableid())){
            return ServerResponse.createServerResponseByFail("请选择患者");
        }
//        System.out.println("=============="+doctor.getTableid());
//        System.out.println(doctorService.isDoctorAlive(doctor.getTableid()));
//        if(!doctorService.isDoctorAlive(doctor.getTableid())){
//            return ServerResponse.createServerResponseByFail("这个医生不存在");
//        }
        Patientcase patientcase = patientcaseMapper.selectByPatientId(GDSessionUtils.getPatientSession(session).getTableid());
        patientcase.setDoctorid(doctor.getTableid());
        int row = patientcaseMapper.updateByPrimaryKeySelective(patientcase);
        Patient patient = patientMapper.selectByPrimaryKey(GDSessionUtils.getPatientSession(session).getTableid());
        patient.setDoctorid(doctor.getTableid());
        int rowPatient = patientMapper.updateByPrimaryKeySelective(patient);
        if(row<1){
            throw new Exception("更新失败");
        }
        if(rowPatient<1){
            throw new Exception("更新失败");
        }
        return ServerResponse.createServerResponseBySucces("更新成功",patientcase);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public ServerResponse sendDateSorce(Patient patient) {
        boolean flag = false;
        SimpleMailMessage msg = new SimpleMailMessage();
        StringBuilder stringBuilder = new StringBuilder(patient.getName());
        stringBuilder.append("日常数据项超标，请您注意查看");
        try{
            msg.setSubject("数据异常通知"); // 邮件主题
            msg.setFrom(mailProperties.getUsername()); // 邮件发送者
            msg.setTo("1161107215@qq.com"); // 邮件接收者，可以有多个
            msg.setSentDate(new Date()); // 邮件发送日期
            msg.setText(stringBuilder.toString()); // 邮件正文
            javaMailSender.send(msg);
            flag = true;
            patient.setUpset("1");
            patientMapper.updateByPrimaryKeySelective(patient);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(flag == false){
                return ServerResponse.createServerResponseByFail("发送失败");
            }
            return ServerResponse.createServerResponseBySucces("发送成功",msg);
        }
    }

    @Override
    public ServerResponse updatePatientMessage(Patient patient) {
        int row = patientMapper.updateByPrimaryKeySelective(patient);
        if(row<1){
            return ServerResponse.createServerResponseByFail("个人信息更新失败");
        }
        return ServerResponse.createServerResponseBySucces("个人信息更新成功",patient);
    }
}
