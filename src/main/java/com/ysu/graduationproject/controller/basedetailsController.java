package com.ysu.graduationproject.controller;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.dao.BasedetailMapper;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.service.Ibasedetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
public class basedetailsController {

    @Autowired
    Ibasedetails ibasedetails;

    //患者记录日常数据
    @PostMapping("/patientBaseRecord")
    public ServerResponse patientBaseRecord(@RequestBody Basedetail basedetail){
        return ibasedetails.patientBaseRecord(basedetail);
    }

    //患者查看今日结果
    @PostMapping("/patientSearchTodayResult")
    public ServerResponse patientSearchTodayResult(){
        return ibasedetails.patientSearchTodayResult();
    }

    //系统分析日常数据
    @PostMapping("/patientDataAnalysis")
    public ServerResponse patientDataAnalysis(@RequestBody Basedetail basedetail){
        return ibasedetails.patientDataAnalysis(basedetail);
    }
}
