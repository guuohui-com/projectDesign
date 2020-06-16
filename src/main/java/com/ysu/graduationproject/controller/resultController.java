package com.ysu.graduationproject.controller;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.po.Result;
import com.ysu.graduationproject.service.IresultService;
import com.ysu.graduationproject.utils.GDSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("result")
public class resultController {

    private String patientId;

    @Autowired
    IresultService iresultService;

    /*
    * 医生写分析结果
    * */
    @PostMapping("/createResult")
    public ServerResponse createResult(@RequestBody Result result){
        return iresultService.createResult(result);
    }

    /*
    * 患者查看分析结果
    * */
    @PostMapping("/selectResult")
    public ServerResponse selectResult(HttpSession session){
        return iresultService.selectResult(session);
    }
}
