package com.netease.exam.myshop.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DataSourceConfig
{

    @Bean
    @Primary
    public DataSourceProperties dataSourceProperties(){
        DataSourceProperties properties = new DataSourceProperties();
        return properties;
    }

    /**
     * 手工创建datasource的目的是明确指明要使用的数据库连接池是tomcat-jdbc
     */
    @Bean
    public DataSource dataSource(DataSourceProperties properties)
    {
        // 使用springboot手册中提供的方法构建自定义的datasource。
        return (DataSource) properties.initializeDataSourceBuilder().type(DataSource.class).build();
    }
}
