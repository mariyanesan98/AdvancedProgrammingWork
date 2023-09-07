package com.service;

import com.dto.request.EmailReq;

public interface IEmailNotify {
    public String sendEmail(EmailReq emailReq);
}
