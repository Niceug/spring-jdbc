package com.ltp.spring_jdbc_template.dao;

import com.ltp.spring_jdbc_template.entity.MemoGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoGroupOperation {

    /**
     * 创建便签组
     * @param memoGroup 便签组对象
     * @return 查询记录数
     */
    int createMemoGroup(MemoGroup memoGroup);


    /**
     * 更新便签组
     * @return 修改记录数
     */
    int updateMemoGroup(int id, String name);

    /**
     * 查询便签组
     * @return 便签组列表
     */
    List<MemoGroup> queryMemoGroup();

    /**
     * 删除便签组
     * @param id 便签组Id
     * @return 删除记录数
     */
    int deleteMemoGroup(int id);

    /**
     * 通过便签组id查询便签数量
     * @param groupId 便签组id
     * @return 便签数量
     */
    int queryMemoByMemoGroupId(int groupId);

    /**
     *根据便签组id，更新便签的便签组id
     * @param groupId
     * @return
     */
    int updateMemoByMemoGroupId(int groupId);

}











