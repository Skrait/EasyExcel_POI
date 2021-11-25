package com.peekaboo.Entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.util.Date;

@Data
@ExcelTarget("emp")
public class Emp {

    @Excel(name = "编号")
    private String id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "生日",format = "yyyy-MM-dd HH:mm:ss")
    private Date bir;

    @Excel(name = "年龄")
    private Integer age;

    @Excel(name = "状态",replace = {"激活_1","锁定_2"})
    private String status;

}
