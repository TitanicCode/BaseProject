package com.common.activititable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * Created by Administrator on 2018/8/4.
 */
//生成工作流的表
public class CreateTable {


    //生成设计表方式一：非配置形式
    public void createTable1(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //设置数据库的4个属性
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti?characterEncoding=utf-8");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123");
        //设置支持表的更新
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //得到流程引擎对象
        ProcessEngine processEngine = configuration.buildProcessEngine();
    }

    //生成设计表方式二：xml配置形式
    public void createTable2(){
        //加载配置
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //得到流程引擎对象
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
    }

    //创建表的启动主函数
    public static void main(String[] args) {
        CreateTable createTable = new CreateTable();
        //createTable.createTable1();
        createTable.createTable2();
    }
}
