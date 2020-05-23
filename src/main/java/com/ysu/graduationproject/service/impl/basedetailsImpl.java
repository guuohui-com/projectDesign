package com.ysu.graduationproject.service.impl;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.dao.BasedetailMapper;
import com.ysu.graduationproject.dao.DetailsMapper;
import com.ysu.graduationproject.dao.PatientMapper;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Details;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.service.Ibasedetails;
import com.ysu.graduationproject.utils.GDSessionUtils;
import com.ysu.graduationproject.utils.GDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.UUID;

@Service
public class basedetailsImpl implements Ibasedetails {

    @Autowired
    DetailsMapper detailsMapper;

    @Autowired
    BasedetailMapper basedetailMapper;

    @Autowired
    PatientServiceImpl patientService;

    @Autowired
    PatientMapper patientMapper;

    private String patientId = "05ffb4a1-0469-4b19-bf5b-ef3fd2c7cd60";


    //通过detailsId找到patientId
    public Patient findPatient(String detailsId){
        return patientMapper.findPatientId(detailsId);
    }

    //通过patientId找到detailsId
    public String findDetailsId(String patientId){
        return basedetailMapper.findDetailsId(patientId);
    }
    @Override
    public ServerResponse patientBaseRecord(Basedetail basedetail) {

            if ((!(GDUtils.isNull(basedetail.getBloodsuger()))) && GDUtils.isNull(basedetail.getBloodsugertype())){
                return ServerResponse.createServerResponseByFail("请选择血糖测量时间");
            }
            return  patientDataAnalysis(basedetail);
    }

    @Override
    public ServerResponse patientDataAnalysis(Basedetail basedetail ) {
        boolean flag = false;
        ServerResponse serverResponse = new ServerResponse();
        //通过patientId找到details和临界值
       ;
        Details details = null;
        try {
            details = detailsMapper.selectByPrimaryKey(findDetailsId(this.patientId));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println("details"+"=================="+details);
//        System.out.println("basedetail"+"=================="+basedetail);
        //比较并且将血压，血糖，体重层次插入
        if(!GDUtils.isNull(basedetail.getBloodpressureHeigh())) {//分析血压
            switch (BloodPressSort(basedetail.getBloodpressureHeigh(), basedetail.getBloodpressureLow())) {
                case 1: {
                    basedetail.setBloodPressLevel("正常血压");
                    break;
                }
                case 2: {
                    basedetail.setBloodPressLevel("轻度高血压");
                    break;
                }
                case 3: {
                    basedetail.setBloodPressLevel("中度高血压");
                    break;
                }
                case 4: {
                    basedetail.setBloodPressLevel("重度高血压");
                    break;
                }
            }
            System.out.println("details"+"=================="+details);
            System.out.println("basedetail"+"=================="+basedetail);
            if (basedetail.getBloodpressureHeigh() > details.getCriticalBloodPressureHeigh() ||
                    basedetail.getBloodpressureLow() > details.getCriticalBloodPressureLow()) {
                flag = true;
            }
        }
        if(!GDUtils.isNull(basedetail.getBloodsuger())) {//分析血糖
            switch (BloodSugerSort(basedetail.getBloodsuger(),basedetail.getBloodsugertype())){
                case 1:{
                    basedetail.setBloodSugerLevel("正常血糖");
                    break;
                }
                case 2:{
                    basedetail.setBloodSugerLevel("糖耐量异常");
                    break;
                }
                  case 3:{
                      basedetail.setBloodSugerLevel("轻度糖尿病");
                    break;
                }
                  case 4:{
                      basedetail.setBloodSugerLevel("中度糖尿病");
                    break;
                }
                  case 5:{
                      basedetail.setBloodSugerLevel("重度糖尿病");
                    break;
                }
                  case 6:{
                      basedetail.setBloodSugerLevel("正常血糖");
                    break;
                }
                  case 7:{
                      basedetail.setBloodSugerLevel("糖耐量异常");
                    break;
                }
                  case 8:{
                      basedetail.setBloodSugerLevel("中度糖尿病");
                    break;
                }
                  case 9:{
                      basedetail.setBloodSugerLevel("重度糖尿病");
                    break;
                }
            }
            if((basedetail.getBloodsugertype()=="空腹"&&basedetail.getBloodsuger()>details.getCriticalFastingBloodSuger())||(basedetail.getBloodsugertype()=="餐后两小时"&&basedetail.getBloodsuger()>details.getCriticalPostprandialBloodSuger())){
                flag = true;
            }
        }

        if(flag == true){
            //发送给医生
            patientService.sendDateSorce(findPatient(details.getTableid()));
        }

        if(patientSearchTodayResult().getMsg()=="查询成功"){
            int row = basedetailMapper.updateByCreateTime(basedetail);
            if(row<1){
                return ServerResponse.createServerResponseByFail("日常数据更新失败");
            }
            return ServerResponse.createServerResponseBySucces("日常数据更新成功",basedetail);
        }

        if(patientSearchTodayResult().getMsg()=="今日未录入数据"){
            basedetail.setTableid(UUID.randomUUID().toString());
            try {
                basedetail.setDetailsid(findDetailsId(this.patientId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int row = basedetailMapper.insertSelective(basedetail);
            if(row <1){
                ServerResponse.createServerResponseByFail("日常数据更新失败");
            }
            return ServerResponse.createServerResponseBySucces("日常数据更新成功",basedetail);
        }
        return null;
    }

    @Override
    public ServerResponse patientSearchTodayResult() {
        Basedetail basedetail = basedetailMapper.patientSearchTodayResult();
        if (GDUtils.isNull(basedetail)){
            return ServerResponse.createServerResponseByFail("今日未录入数据");
        }
        return ServerResponse.createServerResponseBySucces("查询成功",basedetail);
    }

    public int BloodPressSort(double heigh,double low){
        if((heigh<120||heigh==120 )&& (low<80||low==80)){
            return 1;//正常血压
        }
        if((heigh>120&&(heigh<140||heigh==140 )) || low>80&&(low<100||low==100)){
            return 2;//轻度高血压
        }
        if((heigh>140&&(heigh<170||heigh==170 )) || low>100&&(low<110||low==110)){
            return 3;//中度高血压
        }
        return 4;//重度高血压
    }

    public int BloodSugerSort(double suger,String type){
        if("空腹".equals(type)){
            if (suger<6.1){
                return 1;//正常血糖
            }
            if((suger>6.1||suger==6.1) && suger<7.0){
                return 2;//糖耐量异常
            }
            if((suger>7.0||suger==7.0) && suger<8.4){
                return 3;//轻度糖尿病
            }
            if((suger>8.4||suger==8.4) && suger<11.1){
                return 4;//中度糖尿病
            }
            return 5;//重度糖尿病
        }
        if("餐后两小时".equals(type)){
            if (suger<7.8){
                return 6;//正常血糖
            }
            if((suger>7.8||suger==7.8) && suger<11.1){
                return 7;//糖耐量异常
            }
            if((suger>11.1||suger==11.1) && suger<13.9){
                return 8;//中度糖尿病
            }
            return 9;//重度糖尿病
        }
       return 0 ;
    }
}
