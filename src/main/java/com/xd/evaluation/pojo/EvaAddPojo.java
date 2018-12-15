package com.xd.evaluation.pojo;

import lombok.Data;

/**
 * @Description: 接收添加评价的业务对象
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 20:04 2018/12/15
 */
@Data
public class EvaAddPojo {

    private Long userId;

    private String evaluationContent;

    private String teacherName;

    private String courseName;

    private Integer courseType; // 课程类别，10：校选修 11：必修 12：实验

    private Boolean isRecommended;  // true: 推荐
}
