package com.ltp.spring_jdbc_template;

import com.ltp.spring_jdbc_template.entity.MemoGroup;
import com.ltp.spring_jdbc_template.service.MemoGroupService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;


/**
 * 测试代码
 */
public class JDBCTemplateApplication {

    private static ApplicationContext context;
    private static MemoGroupService memoGroupService;
    private static Logger logger = LoggerFactory.getLogger(JDBCTemplateApplication.class);

    @BeforeClass
    public static void beforeClass() {
        context =
                new ClassPathXmlApplicationContext("application-context.xml");
        memoGroupService = context.getBean(MemoGroupService.class);
    }


    @Test
    public void test_createMemoGroup() {
        MemoGroup memoGroup = new MemoGroup();
        memoGroup.setName("Leeeee");
        memoGroup.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        memoGroupService.createMemoGroup(memoGroup);
        logger.debug("insert into memogroup name, createdTime");
    }

    @Test
    public void test_updateMemoGroup() {
        memoGroupService.updateMemoGroup(16, "Leetp");
    }

    @Test
    public void test_queryMemoGroup() {
        List<MemoGroup> list = memoGroupService.queryMemoGroup();
        logger.debug("show memogroup list {}", list);
    }

    @Test
    public void test_deleteMemoGroup() {

    }

}
