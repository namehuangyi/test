package com.powernode.ssm.service;

import com.powernode.ssm.bean.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: UserService
 * Description:
 * Datetime: 2024/4/1 15:45
 * Author: 老杜@动力节点
 * Version: 1.0
 */
@Transactional
public interface UserService {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getById(Long id);

}