package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public interface Ibasedetails {
    ServerResponse patientBaseRecord(Basedetail basedetail);

    ServerResponse patientDataAnalysis(Basedetail basedetail);

    ServerResponse patientSearchTodayResult();
}
