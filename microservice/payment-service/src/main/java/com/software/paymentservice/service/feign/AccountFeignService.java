package com.software.paymentservice.service.feign;


import com.software.commonservice.common.ResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@FeignClient(name = "${feign.remote.account-service}",path = "/rest/account")
public interface AccountFeignService {
    /**
     * 根据用户id获取账户余额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getAccountBalanceByUserId",method = RequestMethod.GET)
    ResultData getAccountBalanceByUserId(@RequestParam(value = "userId") Integer userId);


    /**
     * 更新用户账户余额
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/updateUserAccBalanceByUserId",method = RequestMethod.GET)
    ResultData updateUserAccBalanceByUserId(@RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "amount") BigInteger amount);
}
