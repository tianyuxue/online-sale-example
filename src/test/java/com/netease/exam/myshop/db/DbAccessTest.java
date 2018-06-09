package com.netease.exam.myshop.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DbAccessTest {
    private static final Logger log = LoggerFactory.getLogger(DbAccessTest.class);

    @Autowired
    private DataSource dataSource;

    @Test
    public void testAccessDev()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int rowCount = jdbcTemplate.queryForObject("select count(*) from person", Integer.class);
        log.info("===========================: " + String.valueOf(rowCount));
    }

    @Test
    public void testAccessPro()
    {

    }


}
