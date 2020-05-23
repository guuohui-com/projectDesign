package com.ysu.graduationproject.utils;

import com.ysu.graduationproject.po.Patient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionAttributes
public class GDSessionUtils {

    public static Patient patientGetSession(@ModelAttribute("patient") Patient patient) throws Exception {
        return patient;
    }


}
