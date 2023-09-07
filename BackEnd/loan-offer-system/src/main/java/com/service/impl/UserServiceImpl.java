package com.service.impl;

import com.dao.IUserDAO;
import com.dto.request.EmailReq;
import com.dto.request.UserLoginReq;
import com.dto.response.CommonResponse;
import com.dto.response.GeneralResponse;
import com.dto.user.request.CreateNewUserReq;
import com.dto.user.request.GetCustomerDetailReq;
import com.dto.user.response.CustomerRes;
import com.service.IEmailNotify;
import com.service.IUserService;
import com.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements IUserService {
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    IUserDAO userDAO;
    @Autowired
    IEmailNotify emailNotify;

    @Override
    public GeneralResponse createNewUser(CreateNewUserReq createNewUserReq) {
        String randomPassword = CommonUtil.generateRandomPasscode();
        logger.info("randomPassword-------------->" + randomPassword);
        createNewUserReq.setSecretKey(randomPassword);
        GeneralResponse response = userDAO.createNewUser(createNewUserReq);
        if (response.isRes()) {
            /*Send Password to User via email*/
            EmailReq emailReq = new EmailReq();
            emailReq.setReceiver(createNewUserReq.getUserEmail());
            String bodyText = "Hi " + createNewUserReq.getLastName() + "\n" +
                    "Please find your loan offer system login password is :" + randomPassword
                    + "\n" + "Thanks, LoanOfferAdmin";
            logger.info("body--------------->" + bodyText);
            emailReq.setMessageBody(bodyText);
            emailReq.setSubject("LoanOfferSystem[ Login Password ]");
            emailNotify.sendEmail(emailReq);
        }
        return response;
    }

    @Override
    public CustomerRes getCustomerDetail(GetCustomerDetailReq getCustomerDetailReq) {
        return userDAO.getCustomerDetail(getCustomerDetailReq);
    }

    @Override
    public List<CustomerRes> getCustomerList() {
        return userDAO.getCustomerList();
    }

    @Override
    public CommonResponse login(UserLoginReq loginReq) {
        return userDAO.login(loginReq);
    }
}
