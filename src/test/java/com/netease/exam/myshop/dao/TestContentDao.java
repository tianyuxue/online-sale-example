package com.netease.exam.myshop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import com.netease.exam.myshop.meta.Content;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TestContentDao
{
    @Resource
    private ContentDao dao;
    
    @Test
    @Transactional
    public void testCRUD()
    {
        int origalCount = dao.findAll().size();
        Content nc  = new Content();
        nc.setContentAbstract("test abstract");
        nc.setPrice(9999.99);
        nc.setText("test text");
        nc.setIcon("aaaabbbcccc");
        nc.setTitle("title");
        dao.addEntity(nc);
        
        Content content = dao.findById(nc.getContentId());
        assertNotNull(content);

        Content nc1  = new Content();
        nc1.setContentAbstract("test abstract");
        nc1.setPrice(9999.99);
        nc1.setText("test text");
        nc1.setTitle("title1");
        nc1.setIcon("aaabbbccc");
        dao.addEntity(nc1);
        

        List<Content> contents = dao.findAll();
        for(Content c : contents)
        {
            System.out.println(c);
        }
        assertEquals(contents.size()-origalCount, 2);
        
        Content nc2 = dao.findById(nc1.getContentId());
        nc2.setContentAbstract("modified content");
        System.out.println(dao.updateEntity(nc2));
        assertEquals("modified content",dao.findById(nc2.getContentId()).getContentAbstract());

        dao.deleteEntity(nc2);
        contents = dao.findAll();
        assertEquals(contents.size()-origalCount, 1);
    }

}
