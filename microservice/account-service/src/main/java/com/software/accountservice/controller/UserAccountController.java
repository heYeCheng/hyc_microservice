package com.software.accountservice.controller;

import com.software.accountservice.service.AccountService;
import com.software.commonservice.common.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("/rest/account")
public class UserAccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 根据userId获取账户余额
     * @param userId
     * @return
     */
    @RequestMapping("/getAccountBalanceByUserId")
    public ResultData getUserAccBalanceByUserId(Integer userId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(accountService.getUserBalanceByUserId(userId));
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用查询用户账户余额接口异常");
        }

        return  resultData;
    }


    /**
     * 更新用户账户余额
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping("/updateUserAccBalanceByUserId")
    public ResultData updateUserAccBalanceByUserId(Integer userId, BigInteger amount){
        ResultData resultData = new ResultData();

        try {
            accountService.updateUserBalanceByUserId(userId,amount);
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用更新用户账户余额接口异常");
        }

        return  resultData;
    }
}
