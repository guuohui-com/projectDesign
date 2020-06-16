package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface IdoctorService {
    ServerResponse doctorRegister(Doctor doctor);

    ServerResponse doctorLogin(Doctor doctor);

    ServerResponse updateNumb(Doctor doctor, HttpSession session);

    ServerResponse selectPatient(HttpSession session);

    ServerResponse selectPatientCase(Patient patient);

    ServerResponse updatePatientType(Patient patient);

    ServerResponse notifyPatient(Patient patient);

    ServerResponse inputMoreDetail(MoreDetailsVO moreDetailsVO);

    ServerResponse updateCriticalDetail(Details details);

    ServerResponse showPatientTend(Patient patient);
}
