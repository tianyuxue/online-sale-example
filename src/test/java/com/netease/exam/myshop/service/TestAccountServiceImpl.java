package com.netease.exam.myshop.service;

import com.netease.exam.myshop.dao.ContentDao;
import com.netease.exam.myshop.dao.TrxDao;
import com.netease.exam.myshop.meta.Content;
import com.netease.exam.myshop.meta.Trx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAccountServiceImpl {

    @Resource
    private AccountService accountService;

    @Resource
    private TrxDao trxDao;

    @Resource
    private ContentDao contentDao;

    @Test
    public void testCheckout()
    {
        //先加入一个商品
        Content nc  = new Content();
        nc.setContentAbstract("test abstract");
        nc.setPrice(9999.99);
        nc.setText("test text");
        nc.setIcon("aaaabbbcccc");
        nc.setTitle("title");
        int contentId = contentDao.addEntity(nc);

        //检查商品是否正确购买
        List<Trx> trxs = trxDao.findAll();
        accountService.checkOut(1,1);
        List<Trx> new_trxs = trxDao.findAll();
        assertEquals(trxs.size(), new_trxs.size()-1);
        System.out.println(new_trxs);
    }
}
