package com.yang.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("teacher")
public class Teacher{
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * NAME
     */
    private String name;
    /**
     * SEX
     */
    private Boolean sex;
    /**
     * AGE
     */
    private Integer age;
    /**
     * 乐观锁
     */
    @Version
    private Integer revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}