package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.*;
import org.springframework.stereotype.Service;

@Service
public interface IdoctorService {
    ServerResponse doctorRegister(Doctor doctor);

    ServerResponse doctorLogin(Doctor doctor);

    ServerResponse updateNumb(Doctor doctor);

    ServerResponse selectPatient();

    ServerResponse selectPatientCase(Patient patient);

    ServerResponse updatePatientType(Patientcase patient);

    ServerResponse notifyPatient(Patient patient);

    ServerResponse inputMoreDetail(Moredetails moredetails);

    ServerResponse updateCriticalDetail(Details details);

    ServerResponse showPatientTend(Patient patient);
}
