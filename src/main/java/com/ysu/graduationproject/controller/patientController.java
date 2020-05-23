package com.ysu.graduationproject.controller;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Doctor;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.service.IdoctorService;
import com.ysu.graduationproject.service.IpatientService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("patient")
public class patientController {

    @Autowired
    IpatientService ipatientService;

    @Autowired
    IdoctorService idoctorService;
    /*
    * 患者的注册
    * */
    @RequestMapping("/patientRegister")
    @ResponseBody
    public ServerResponse patientRegister(@RequestBody Patient patient){
        try {
           return ipatientService.patientRegister(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createServerResponseByFail("注册失败");
    }

    /*
     * 患者的登录
     * */
    @RequestMapping("/patientLogin")
    @ResponseBody
    public ServerResponse patientLogin(@RequestBody Patient patient, HttpSession session){
         ServerResponse serverResponse = ipatientService.patientLogin(patient);
         session.setAttribute("patient",serverResponse.getData());
         return serverResponse;
    }


    /*
    * 患者修改个人信息
    * */
    @RequestMapping("/updatePatientMessage")
    @ResponseBody
    public ServerResponse updatePatientMessage(@RequestBody Patient patient){
        return ipatientService.updatePatientMessage(patient);
    }

    /*
     * 患者查看所有的医生信息
     * */
    @RequestMapping("/patientSelectDoctor")
    @ResponseBody
    public ServerResponse patientSelectDoctor(){
        return ipatientService.patientSelectDoctor();
    }

    /*
    * 患者选择医生
    * */
    @RequestMapping("/patientChooseDoctor")
    @ResponseBody
    public ServerResponse patientChooseDoctor(@RequestBody Doctor doctor){
        try {
            return ipatientService.patientChooseDoctor(doctor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createServerResponseByFail("注册失败");
    }

    /*
     * 患者产看个人病例历史
     * */
    @PostMapping("/selectPatientCase")
    public ServerResponse selectPatientCase(@RequestBody Patient patient){
        return idoctorService.selectPatientCase(patient);
    }

    /*
     * 患者向医生上传数据
     * */
    @PostMapping("/sendDateSorce")
    public ServerResponse sendDateSorce(@RequestBody Patient patient){
        return ipatientService.sendDateSorce(patient);
    }

    /*
    *患者的病情走势图
    * */
    @PostMapping("/showPatientTend")
    public ServerResponse showPatientTend(@RequestBody Patient patient){
        return idoctorService.showPatientTend(patient);
    }

}
