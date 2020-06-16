package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Basedetail;
import com.ysu.graduationproject.po.Doctor;
import com.ysu.graduationproject.po.Patient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface IpatientService {
    ServerResponse patientRegister(Patient patient) throws Exception;

    ServerResponse patientLogin(Patient patient);

    ServerResponse patientSelectDoctor();

    ServerResponse patientChooseDoctor(Doctor doctor, HttpSession session) throws Exception;

    ServerResponse sendDateSorce(Patient patient);

    ServerResponse updatePatientMessage(Patient patient);
};