package com.ysu.graduationproject.utils;

import com.ysu.graduationproject.po.Doctor;
import com.ysu.graduationproject.po.Patient;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes
public class GDSessionUtils {
    public static Patient getPatientSession(HttpSession session) {
       return (Patient) session.getAttribute("patient");
    }
    public static Doctor getDoctorSession(HttpSession session) {
        return (Doctor) session.getAttribute("doctor");
    }

}
