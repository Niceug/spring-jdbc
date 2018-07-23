package com.ltp.spring_jdbc_template.service;

import com.ltp.spring_jdbc_template.entity.MemoGroup;

import java.util.List;

public interface MemoGroupService {
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

}
