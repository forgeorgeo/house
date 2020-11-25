package com.house.vo;

import java.util.List;
import lombok.Data;

/**
 * @Author Tim
 * @Date 2020/10/22 16:52
 */
@Data
public class CourseHoursVO {
    private Integer totalHours;
    private List<WorkLoadVO> workLoadVO;
}
