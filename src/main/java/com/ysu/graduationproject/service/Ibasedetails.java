package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Service
public interface Ibasedetails {
    ServerResponse patientBaseRecord(Basedetail basedetail,HttpSession session);

    ServerResponse patientDataAnalysis(Basedetail basedetail, HttpSession session);

    ServerResponse patientSearchTodayResult(HttpSession session);
}
