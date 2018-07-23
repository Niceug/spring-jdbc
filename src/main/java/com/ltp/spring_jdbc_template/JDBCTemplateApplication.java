package com.ltp.spring_jdbc_template;

import com.ltp.spring_jdbc_template.dao.MemoGroupOperation;
import com.ltp.spring_jdbc_template.entity.MemoGroup;
import com.ltp.spring_jdbc_template.service.MemoGroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class JDBCTemplateApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MemoGroupOperation springJdbcOperation = context.getBean(MemoGroupOperation.class);
//        MemoGroup memoGroup = new MemoGroup();
//        memoGroup.setName("tmd");
//        memoGroup.setCreatedTime(new Date());
//        memoGroup.setModifyTime(new Date());
//        springJdbcOperation.createMemoGroup(memoGroup);

        // 查询
        List<MemoGroup> list = springJdbcOperation.queryMemoGroup();
        System.out.println(list);

//        // 删除
//        int effect = springJdbcOperation.deleteMemoGroup(13);
//        System.out.println(effect);

        // 更新
        int effect = springJdbcOperation.updateMemoGroup(14, "jjj");
        System.out.println(effect);


        // 服务层调用方法
        MemoGroupService memoGroupService = context.getBean(MemoGroupService.class);

        List<MemoGroup> list1 = memoGroupService.queryMemoGroup();
        System.out.println(list1);

        memoGroupService.deleteMemoGroup(14);

    }
}
