package com.ltp.spring_jdbc_template.entity;

import com.ltp.spring_jdbc_template.comment.Background;
import lombok.Data;

import java.util.Date;

/**
 * Author: Leetp
 * Created: 2018/7/23
 */
@Data
public class MemoInfo {
    private Integer id;
    private Integer groupId;
    private String title;
    private String content;
    private Character privacy;
    private Background backGround;
    private Character remind;
    private Date remindTime;
    private Date createdTime;
    private Date modifyTime;
}
