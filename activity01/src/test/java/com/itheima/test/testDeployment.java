package com.itheima.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.List;

public class testDeployment {
    /**
     * 测试流程部署
     */
    @Test
    public void testDeployment(){
//        1、创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2、获取repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
//        3、使用service进行流程的部署，定义一个流程的名字，把bpmn和png部署到数据中
        Deployment deploy = repositoryService.createDeployment()
                .name("出差申请流程")
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .deploy();
//        4、输出部署信息
        System.out.println("流程部署id："+deploy.getId());
        System.out.println("流程部署名字："+deploy.getName());
        System.out.println();
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess(){
//        1、创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2、获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
//        3、根据流程定义的id启动流程
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");
//        4、输出内容
        System.out.println("流程定义id："+instance.getProcessDefinitionId());
        System.out.println("流程实例id："+instance.getId());
        System.out.println("当前活动的id："+instance.getActivityId());

    }

    /**
     * 个人任务查询功能
     */
    @Test
    public void testFindPersonalTaskList(){
//        1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        2、获取taskService
        TaskService taskService = processEngine.getTaskService();
//        3、根据流程key和任务的负责人查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myEvection")
                .taskAssignee("zhangsan")
                .list();
//        4、输出
        for (Task task : taskList) {
            System.out.println("任务流程id："+task.getProcessInstanceId());
            System.out.println("任务id："+task.getId());
            System.out.println("任务负责人："+task.getAssignee());
            System.out.println("任务名称："+task.getName());
        }
    }

}
