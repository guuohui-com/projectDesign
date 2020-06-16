package com.ysu.graduationproject.controller;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.*;
import com.ysu.graduationproject.service.IdoctorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("doctor")
public class doctorController {

    @Autowired
    IdoctorService idoctorService;

    //医生的注册
    @PostMapping("/doctorRegister")
    public ServerResponse doctorRegister(@RequestBody Doctor doctor){
        return idoctorService.doctorRegister(doctor);
    }

    //医生的登录
    @PostMapping("/doctorLogin")
    public ServerResponse doctorLogin(@RequestBody Doctor doctor,HttpSession session){
        ServerResponse serverResponse = idoctorService.doctorLogin(doctor);
        session.setAttribute("doctor",serverResponse.getData());
        return serverResponse;
    }

    //医生修改管理病人数
    @PostMapping("/updateNumb")
    public ServerResponse updateNumb(@RequestBody Doctor doctor, HttpSession session){
        return idoctorService.updateNumb(doctor,session);
    }

    //医生查看管理的患者信息
    @PostMapping("/selectPatient")
    public ServerResponse selectPatient(HttpSession session){
        return idoctorService.selectPatient(session);
    }

    //查看单个患者的病例
    @PostMapping("/selectPatientCase")
    public ServerResponse selectPatientCase(@RequestBody Patient patient){
        return idoctorService.selectPatientCase(patient);
    }

    //填写单个人的糖尿病类型
    @PostMapping("/updatePatientType")
    public ServerResponse updatePatientType(@RequestBody Patient patient){
        return idoctorService.updatePatientType(patient);
    }

    //医生传唤患者
    @PostMapping("/notifyPatient")
    public ServerResponse notifyPatient(@RequestBody Patient patient){
        return idoctorService.notifyPatient(patient);
    }

    //医生给患者插入MoreDetail
    @PostMapping("/inputMoreDetail")
    public ServerResponse inputMoreDetail(@RequestBody MoreDetailsVO moreDetailsVO){
        System.out.println("==========inputMoreDetail=============");
        return idoctorService.inputMoreDetail(moreDetailsVO);
    }

    //医生修改Details中的临界值
    @PostMapping("/updateCriticalDetail")
    public ServerResponse updateCriticalDetail(@RequestBody Details details){
        return idoctorService.updateCriticalDetail(details);
    }
}
