package com.peekaboo.Entity;

import cn.afterturn.easypoi.excel.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ExcelTarget("users")
public class User implements Serializable {

    @Excel(name = "编号",orderNum ="0")
    private String id;

    @Excel(name = "姓名",orderNum ="1")
    private String name;

    @Excel(name = "年龄",orderNum ="3")
    private Integer age;

    @Excel(name = "生日",width = 35.0, format =" yyyy-MM-dd HH:mm:ss",orderNum = "2")
    private Date bir;

    @Excel(name = "状态",replace = {"激活_1","锁定_0"},orderNum = "4")
    private String status;

    //@Excel(name = "爱好",width = 20,orderNum = "5")
    @ExcelIgnore
    private List<String> habbys;//喜好

//    @Excel(name="爱好",width = 20.0,orderNum = "5")
//    private String habbyString;//爱好字符串

//    public String gethabbyString() {
//        StringBuilder sb = new StringBuilder();
//        habbys.forEach(e->{
//            sb.append(e).append("、");
//        });
//        return sb.toString();
//    }

    @Excel(name="爱好",width = 20.0,orderNum = "5")
    private String habbyString; //实体类名首字母要小写

    public String gethabbyString() {
        StringBuilder sb = new StringBuilder();
        habbys.forEach(e->{
            sb.append(e).append("、");
        });
        return sb.toString();
    }

    @ExcelEntity() //标识一对一关系
    private Card card;

    @ExcelCollection(name = "订单信息",orderNum = "8")
    private List<Order> orders;
}
