package com.ysu.graduationproject.service.impl;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.dao.ResultMapper;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Doctor;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.po.Result;
import com.ysu.graduationproject.service.IresultService;
import com.ysu.graduationproject.utils.GDSessionUtils;
import com.ysu.graduationproject.utils.GDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class resultServiceImpl implements IresultService {
    private String patientId = "05ffb4a1-0469-4b19-bf5b-ef3fd2c7cd60";

    @Autowired
    ResultMapper resultMapper;

    @Override
    public ServerResponse createResult(Result result) {
        if(GDUtils.isNull(result)){
            return ServerResponse.createServerResponseByFail("请填写诊断结果");
        }
        int row = resultMapper.insertSelective(result);
        if(row<1){
            return ServerResponse.createServerResponseByFail("插入失败");
        }
        return ServerResponse.createServerResponseBySucces("插入成功",result);
    }

    @Override
    public ServerResponse selectResult() {
        List<Result> resultList = null;
        try {
            resultList = resultMapper.selectResultAll(this.patientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(GDUtils.isNull(resultList)){
            return ServerResponse.createServerResponseByFail("您还没有诊断结果");
        }
        return ServerResponse.createServerResponseBySucces("查询成功",resultList);
    }
}
