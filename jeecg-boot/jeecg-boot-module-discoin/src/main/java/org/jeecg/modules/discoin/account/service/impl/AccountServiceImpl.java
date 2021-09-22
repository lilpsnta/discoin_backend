package org.jeecg.modules.discoin.account.service.impl;

import org.jeecg.modules.discoin.account.entity.Account;
import org.jeecg.modules.discoin.account.mapper.AccountMapper;
import org.jeecg.modules.discoin.account.service.IAccountService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 账户Service组件
 * @Author: lufer
 * @Date: 2021-09-06
 * @Version: V1.0
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
