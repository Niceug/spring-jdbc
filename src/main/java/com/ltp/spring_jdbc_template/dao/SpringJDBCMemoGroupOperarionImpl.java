package com.ltp.spring_jdbc_template.dao;

import com.ltp.spring_jdbc_template.entity.MemoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpringJDBCMemoGroupOperarionImpl implements MemoGroupOperation {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJDBCMemoGroupOperarionImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
                memoGroup.setId(resultSet.getInt(1));
                memoGroup.setName(resultSet.getString(2));
                memoGroup.setCreatedTime(resultSet.getDate(3));
                memoGroup.setModifyTime(resultSet.getDate(4));
                return memoGroup;
            }
        });
        return list;
    }

    public int deleteMemoGroup(int id) {
        int effect = this.jdbcTemplate.update("delete from memo_group where id = ?");
        return effect;
    }

    public int queryMemoByMemoGroupId(int groupId) {
        int count = this.jdbcTemplate.queryForObject("select count(memo_info.id) from memo_info, memo_group where memo_info.group_id = memo_group.id and memo_group.id = ?",
                Integer.class, groupId);
        return count;
    }

    public int updateMemoByMemoGroupId(int groupId) {
        int count = this.jdbcTemplate.update("update memo_info set group_id = 0 where group_id = ?", groupId);
        return count;
    }
}
