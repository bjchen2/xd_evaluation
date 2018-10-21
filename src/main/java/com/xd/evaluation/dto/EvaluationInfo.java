package com.xd.evaluation.dto;

import com.xd.evaluation.enums.CourseTypeEnum;
import com.xd.evaluation.utils.EnumUtil;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 用于返回评价的信息
 * @Author: Kripath
 * @Modified By:
 * @Date: Created in 8:22 2018/10/16
 */
@Data
public class EvaluationInfo {
    private Long evaluationId;

    private Long userId;

    private String evaluationContent;

    private String teacherName;

    private String courseName;

    private String courseType;

    private Boolean isRecommended;

    private Boolean isFavorited;

    private Boolean isLike;

    private Integer agreeCount;

    private Integer disagreeCount;

    private Integer commentCount;

    private Long createTime;

    public EvaluationInfo(Long evaluationId, Long userId, String evaluationContent,
                          String teacherName, String courseName, String courseType,
                          Boolean isRecommended, Integer agreeCount,
                          Integer disagreeCount, Long createTime) {
        this.evaluationId = evaluationId;
        this.userId = userId;
        this.evaluationContent = evaluationContent;
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.courseType = courseType;
        this.isRecommended = isRecommended;
        this.agreeCount = agreeCount;
        this.disagreeCount = disagreeCount;
        this.createTime = createTime;
    }

    public EvaluationInfo() {};
}
