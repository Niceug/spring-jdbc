package com.ltp.spring_jdbc_template.service;

import com.ltp.spring_jdbc_template.dao.MemoGroupOperation;
import com.ltp.spring_jdbc_template.entity.MemoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemoGroupServiceImpl implements MemoGroupService {
    MemoGroupOperation memoGroupOperation;

    @Autowired
    public MemoGroupServiceImpl (MemoGroupOperation memoGroupOperation) {
        this.memoGroupOperation = memoGroupOperation;
    }
    public int createMemoGroup(MemoGroup memoGroup) {
        int effect = memoGroupOperation.createMemoGroup(memoGroup);
        if(effect == 0) {
            System.out.println("便签组名字已存在");
            return 0;
        }

        System.out.println("插入成功");
        return 1;
    }

    public int updateMemoGroup(int id, String name) {
        int effect = memoGroupOperation.updateMemoGroup(id, name);
        if(effect == 0) {
            System.out.println("便签组名字已存在");
            return 0;
        }

        System.out.println("修改成功");
        return 1;
    }

    public List<MemoGroup> queryMemoGroup() {
        return memoGroupOperation.queryMemoGroup();
    }

    public int deleteMemoGroup(int id) {
        int count = memoGroupOperation.queryMemoByMemoGroupId(id);
        if(count == 0) {
            int effect = memoGroupOperation.deleteMemoGroup(id);
            if(effect == 0) {
                System.out.println("id不存在，删除失败");
                return 0;
            }

            System.out.println("删除成功");
            return 1;
        }


        return 1;
    }
}
