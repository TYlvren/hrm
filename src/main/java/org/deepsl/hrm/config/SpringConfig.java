package org.deepsl.hrm.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
//配置Spring扫描器，且设置@EnableWebMvc注解的不进行扫描
@ComponentScan(basePackages = "org.deepsl.hrm",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class))
public class SpringConfig {

    @Bean
    //注入数据源，方法名是id，DataSource是注入的类型
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hrm_db3?serverTimezone=GMT");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    //注入SqlSessionFactoryBean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource){

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置bean的别名（model层，数据库输入输出的类型）
        sqlSessionFactoryBean.setTypeAliasesPackage("org.deepsl.hrm.domain");
        return sqlSessionFactoryBean;
    }

    @Bean
    //注入映射文件扫描器
    public MapperScannerConfigurer mapperScannerConfigurer(SqlSessionFactoryBean sqlSessionFactoryBean){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("org.deepsl.hrm.dao");

        //设置SqlSessionFactoryBean
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
