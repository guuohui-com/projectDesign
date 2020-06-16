package com.ysu.graduationproject.service;

import com.ysu.graduationproject.common.ServerResponse;
import com.ysu.graduationproject.po.Patient;
import com.ysu.graduationproject.po.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Service
public interface IresultService {
    ServerResponse createResult(Result result);

    ServerResponse selectResult(HttpSession session);
}
