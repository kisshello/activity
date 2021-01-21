package com.itheima.test;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class TestCreate {

    /**
     *使用activity提供的默认方式来创建mysql的表
     */
    @Test
    public void testCreateDbTable(){
        //需要使用activity提供的工具类processEngines，使用方法getDefaultProcessEngine
        //getDefaultProcessEngine会默认从resources下读取名为activity.cfg.xml的文件
//        创建processEngine时，就会创建MySQL的表
//        默认方式
        //ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        ProcessEngineConfiguration processEngineConfigurationFromResource =
                ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration");
        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();

        //System.out.println(processEngine);

    }



}
