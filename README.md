# springboot_Quartz_mybatis
springboot整合quartz实现定时任务的开启，关闭，暂停和恢复的一个小demo

## 项目简介
### 1.项目框架
springboot 2.x + mybatis + quartz

### 2.项目分层

分层还是按照约定分层,主要讲讲service层分层
```yaml
com
  .kevin
    .demo
      .QuartzService : 相当于定时任务的配置类，获取调度器
      . TaskService : 任务业务处理
```
数据库mapper层我还没有写，主要目的还是定时任务的学习

-------------------------------------------------------

#### 更新：

- 时间：2019.05.13

更新内容：

* 完善了任务管理系统的mapper层
* 修复了相关逻辑问题，经测试，任务的动态开启，暂停，恢复和终止都可以正常使用
* 添加了misfirm机制



### 相关个人博客
[github个人博客](https://github.com/Kevin091827/kevin_blog/tree/master/spring%20boot/%E5%AE%9A%E6%97%B6%E4%BB%BB%E5%8A%A1)




