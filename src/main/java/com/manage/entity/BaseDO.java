package com.manage.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.util.Date;

/**
 * Description:基类
 * Author:kbq
 * Date: 2019-12-10 16:05
 */
@Data
public class BaseDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "updated_by")
    private String updatedBy;
}
