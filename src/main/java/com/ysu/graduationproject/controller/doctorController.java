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
    public ServerResponse doctorLogin(@RequestBody Doctor doctor){
        return idoctorService.doctorLogin(doctor);
    }

    //医生修改管理病人数
    @PostMapping("/updateNumb")
    public ServerResponse updateNumb(@RequestBody Doctor doctor){
        return idoctorService.updateNumb(doctor);
    }

    //医生查看管理的患者信息
    @PostMapping("/selectPatient")
    public ServerResponse selectPatient(){
        return idoctorService.selectPatient();
    }

    //查看单个患者的病例
    @PostMapping("/selectPatientCase")
    public ServerResponse selectPatientCase(@RequestBody Patient patient){
        return idoctorService.selectPatientCase(patient);
    }

    //填写单个人的糖尿病类型
    @PostMapping("/updatePatientType")
    public ServerResponse updatePatientType(@RequestBody Patientcase patientcase){
        return idoctorService.updatePatientType(patientcase);
    }

    //医生传唤患者
    @PostMapping("/notifyPatient")
    public ServerResponse notifyPatient(@RequestBody Patient patient){
        return idoctorService.notifyPatient(patient);
    }

    //医生给患者插入MoreDetail
    @PostMapping("/inputMoreDetail")
    public ServerResponse inputMoreDetail(@RequestBody Moredetails moredetails){
        return idoctorService.inputMoreDetail(moredetails);
    }

    //医生修改Details中的临界值
    @PostMapping("/updateCriticalDetail")
    public ServerResponse updateCriticalDetail(@RequestBody Details details){
        return idoctorService.updateCriticalDetail(details);
    }
}
