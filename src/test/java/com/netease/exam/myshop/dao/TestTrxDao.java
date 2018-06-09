package com.netease.exam.myshop.dao;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.List;

import javax.annotation.Resource;

import com.netease.exam.myshop.meta.Content;
import com.netease.exam.myshop.meta.Trx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTrxDao {
    
    @Resource
    TrxDao trxDao;

    @Resource
    ContentDao contentDao;
    
    @Test
    @Transactional
    public void testCRUD(){
        int orignalNum = trxDao.findAll().size();

        Trx trx = new Trx();
        trx.setContentId(1);
        trx.setPersonId(1);
        trx.setPrice(999.9);
        trx.setTime(Instant.now());

        int ret = trxDao.addEntity(trx);
        assertEquals(1, ret);
        assertEquals(orignalNum+1, trxDao.findAll().size());
        System.out.println(trxDao.findById(trx.getTrxId()));

        List<Trx> trxs = trxDao.findAll();
        for(Trx t : trxs){
            System.out.println(t);
        }

        trx.setPrice(100000.0);
        trxDao.updateEntity(trx);
        trxs = trxDao.findAll();
        for(Trx t : trxs){
            System.out.println(t);
        }

        trxDao.deleteEntity(trx);
        trxs = trxDao.findAll();
        assertEquals(orignalNum, trxs.size());
    }

    @Test
    @Transactional
    public void testOthers()
    {
        Trx trx = new Trx();
        trx.setContentId(1);
        trx.setPersonId(1);
        trx.setPrice(9.9);
        trx.setTime(Instant.now());
        trxDao.addEntity(trx);

        Trx trx1 = new Trx();
        trx1.setContentId(2);
        trx1.setPersonId(1);
        trx1.setPrice(100.9);
        trx1.setTime(Instant.now());
        trxDao.addEntity(trx1);

        List<Trx> trxs = trxDao.findAll();
        System.out.println(trxs);

        Content nc  = new Content();
        nc.setContentAbstract("test abstract");
        nc.setPrice(9999.99);
        nc.setText("test text");
        nc.setIcon("aaaabbbcccc");
        nc.setTitle("title");
        contentDao.addEntity(nc);

        Content nc1  = new Content();
        nc1.setContentAbstract("test abstract");
        nc1.setPrice(99.99);
        nc1.setText("text");
        nc1.setIcon("ac");
        nc1.setTitle("t");
        contentDao.addEntity(nc1);

        System.out.println("content表中的所有字段: ");
        List<Content> orignalContents = contentDao.findAll();
        System.out.println(orignalContents);

        System.out.println("指定用户购买的content: ");
        List<Content> contents = trxDao.getContentsOfTargetPerson(1);
        System.out.println(contents);
        assertEquals(2, contents.size());

        System.out.println("特定用户涉及到的所有交易: ");
        List<Trx> trxsOfPerson = trxDao.getTrxsOfTargetPerson(1);
        assertEquals(2, trxsOfPerson.size());
        System.out.println(trxsOfPerson);

        System.out.println("特定content涉及到的所有交易: ");
        List<Trx> trxsOfContent = trxDao.getTrxsOfTargetContent(1);
        assertEquals(1, trxsOfContent.size());
        System.out.println(trxsOfContent);

        System.out.println("特定用户和content涉及到的所有交易: ");
        List<Trx> trxsOfPersonAndContent = trxDao.getTrxsOfTargetPersonAndContent(1,1);
        assertEquals(1, trxsOfPersonAndContent.size());
        System.out.println(trxsOfPersonAndContent);
    }
}
