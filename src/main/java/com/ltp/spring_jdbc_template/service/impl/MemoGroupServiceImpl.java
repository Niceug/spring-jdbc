package com.ltp.spring_jdbc_template.service.impl;

import com.ltp.spring_jdbc_template.dao.MemoGroupOperation;
import com.ltp.spring_jdbc_template.entity.MemoGroup;
import com.ltp.spring_jdbc_template.service.MemoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 事务管理注解
 */
@Service
@Transactional
public class MemoGroupServiceImpl implements MemoGroupService {

    @Autowired
    private MemoGroupOperation memoGroupOperation;

    public int createMemoGroup(MemoGroup memoGroup) {
        if(memoGroup == null || "".equals(memoGroup.getName()) || "".equals(memoGroup.getCreatedTime())) {
            throw new IllegalArgumentException("非法参数");
        }

        int count = memoGroupOperation.queryMemoGroupCountByGroupName(memoGroup.getName());
        if(count > 0) {
            System.out.println("便签组名字已存在");
            return 0;
        }

        int effect = memoGroupOperation.createMemoGroup(memoGroup);
        System.out.println("插入成功");
        return effect;
    }

    public int updateMemoGroup(int id, String name) {
        int count = memoGroupOperation.queryMemoGroupCountByGroupName(name);

        if(count > 0) {
            System.out.println("便签组名字已存在");
            return 0;
        }

        int effect = memoGroupOperation.updateMemoGroup(id, name);
        System.out.println("修改成功");
        return effect;
    }


    @Transactional(readOnly = true, transactionManager = "transactionManager")
    public List<MemoGroup> queryMemoGroup() {
        return memoGroupOperation.queryMemoGroup();
    }

    public int deleteMemoGroup(int id) {
        int count = memoGroupOperation.queryMemoCountByMemoGroupId(id);

        // 要删除的便签组中无便签，直接删除便签组。
        if(count == 0) {
            int effect = memoGroupOperation.deleteMemoGroup(id);
            if(effect == 0) {
                System.out.println("id不存在，删除失败");
                return 0;
            }

            System.out.println("删除成功");
            return effect;
        }

        // 要删除的便签组有便签。
        // 将便签移动到默认组
        memoGroupOperation.updateMemoByMemoGroupId(id);

        // 删除便签组
        int effect = memoGroupOperation.deleteMemoGroup(id);
        if(effect == 0) {
            System.out.println("id不存在，删除失败");
            return 0;
        }

        return effect;
    }
}
