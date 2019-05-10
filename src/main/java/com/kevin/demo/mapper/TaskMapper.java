package com.kevin.demo.mapper;

import com.kevin.demo.entity.MyJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:    任务增删改查
 * @Author:         Kevin
 * @CreateDate:     2019/5/10 0:06
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/5/10 0:06
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Mapper
@Repository
public interface TaskMapper {

    /**
     * 根据任务状态查找任务
     * @param status
     * @return
     */
    MyJob selectTaskByStaus(String status);

    /**
     * 新增任务
     * @param myJob
     * @return
     */
    int insertTask(MyJob myJob);

    /**
     * 通过组别和名称(封装成map)查询任务
     * @return
     */
   MyJob selectTaskByNameAndGroup(Map<String,String> param);

    /**
     * 查询所有任务
     * @return
     */
    List<MyJob> selectAllJob();

    /**
     * 更新任务状态
     * @param myJob
     * @return
     */
    int updateTaskStatus(MyJob myJob);

    /**
     * 更新任务的cron表达式
     * @param myJob
     * @return
     */
    int updateTaskCron(MyJob myJob);

    /**
     * 更新任务信息
     * @param myJob
     * @return
     */
    int updateTaskInfo(MyJob myJob);

    /**
     * 删除指定任务
     * @param map
     * @return
     */
    int deleteTask(Map<String,String> map);
}
