package com.ltp.spring_jdbc_template.dao.impl;

import com.ltp.spring_jdbc_template.comment.Background;
import com.ltp.spring_jdbc_template.dao.MemoGroupOperation;
import com.ltp.spring_jdbc_template.entity.MemoGroup;
import com.ltp.spring_jdbc_template.entity.MemoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpringJDBCMemoGroupOperarionImpl implements MemoGroupOperation {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createMemoGroup(MemoGroup memoGroup) {
        int effect = this.jdbcTemplate.update("insert into memo_group(name, created_time, modify_time) values(?, ?, ?)",
                new Object[]{memoGroup.getName(), memoGroup.getCreatedTime(), memoGroup.getModifyTime()});
        return effect;
    }

    public int updateMemoGroup(int id, String name) {
        int effect = this.jdbcTemplate.update("update memo_group set name = ? where id = ?", name, id);
        return effect;
    }

    public List<MemoGroup> queryMemoGroup() {
        List<MemoGroup> list = this.jdbcTemplate.query("select id, name, created_time, modify_time from memo_group",
                new RowMapper<MemoGroup>() {
            public MemoGroup mapRow(ResultSet resultSet, int i) throws SQLException {
                MemoGroup memoGroup = new MemoGroup();
                memoGroup.setId(resultSet.getInt("id"));
                memoGroup.setName(resultSet.getString("name"));
                memoGroup.setCreatedTime(resultSet.getDate("created_time"));
                memoGroup.setModifyTime(resultSet.getDate("modify_time"));
                return memoGroup;
            }
        });
        return list;
    }

    public int deleteMemoGroup(int id) {
        int effect = this.jdbcTemplate.update("delete from memo_group where id = ?", id);
        return effect;
    }

    public int queryMemoCountByMemoGroupId(int groupId) {
        int count = this.jdbcTemplate.queryForObject("select count(memo_info.id) from memo_info, memo_group where memo_info.group_id = memo_group.id and memo_group.id = ?",
                Integer.class, groupId);
        return count;
    }

    public int queryMemoGroupCountByGroupName(String name) {
        int count = this.jdbcTemplate.queryForObject("select count(id) from memo_group where name = ?", Integer.class, name);
        return count;
    }

    public int updateMemoByMemoGroupId(int groupId) {
        int count = this.jdbcTemplate.update("update memo_info set group_id = 0 where group_id = ?", groupId);
        return count;
    }

    public List<MemoInfo> queryMemoByMemoGroupId(final int memoGroupId) {
        List<MemoInfo> list = this.jdbcTemplate.query("select * from memo_info where group_id = ?", new RowMapper<MemoInfo>() {
            public MemoInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                MemoInfo memoInfo = new MemoInfo();
                memoInfo.setId(resultSet.getInt("id"));
                memoInfo.setGroupId(resultSet.getInt("group_id"));
                memoInfo.setTitle(resultSet.getString("title"));
                memoInfo.setContent(resultSet.getString("content"));
                memoInfo.setPrivacy((Character) resultSet.getObject("is_protected"));
                memoInfo.setBackGround((Background) resultSet.getObject("background"));
                memoInfo.setRemind((Character) resultSet.getObject("is_remind"));
                memoInfo.setRemindTime(resultSet.getTime("remind_time"));
                memoInfo.setCreatedTime(resultSet.getTime("created_time"));
                memoInfo.setModifyTime(resultSet.getTime("modify_time"));
                return memoInfo;
            }
        }, memoGroupId);
        return list;
    }
}
