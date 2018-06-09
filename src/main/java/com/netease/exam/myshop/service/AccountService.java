package com.netease.exam.myshop.service;

import com.netease.exam.myshop.web.model.User;

public interface AccountService {

    /**
     * 获取当前登录的用户
     * @return 返回当前登录的用户信息
     */
    User getCurrentUser();


    /**
     * 执行用户购买购物车中商品的逻辑
     * @param productId 要购买的的product的id
     * @param num 购买的数量
     * @return 成功购买返回true, 否则返回false
     */
    boolean checkOut(int productId, int num);

}
