package com.netease.exam.myshop.service.impl;

import com.netease.exam.myshop.dao.TrxDao;
import com.netease.exam.myshop.meta.Person;
import com.netease.exam.myshop.meta.Trx;
import com.netease.exam.myshop.service.AccountService;
import com.netease.exam.myshop.service.ProductCRUDService;
import com.netease.exam.myshop.utils.SecurityUtils;
import com.netease.exam.myshop.web.model.Product;
import com.netease.exam.myshop.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Instant;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private TrxDao trxDao;

    @Autowired
    private ProductCRUDService productCRUDService;

    @Override
    public User getCurrentUser()
    {
        Person person = SecurityUtils.getCurrentUser();
        if(person != null)
            return new User(person.getUserName(), person.getUserType());
        return null;
    }

    @Override
    @Transactional
    public boolean checkOut(int productId, int num) {
        for(int i=0; i!=num; i++)
        {
            Product product = productCRUDService.getProductById(productId);
            if(product==null)
                return false;

            Person person = SecurityUtils.getCurrentUser();
            if(person==null)
                return false;

            Trx trx = new Trx();
            trx.setPersonId(person.getUserId());
            trx.setContentId(productId);
            trx.setPrice(product.getPrice());
            trx.setTime(Instant.now());

            if(trxDao.addEntity(trx) == 0)
                return false;
        }
        return true;
    }
}
