package com.peekaboo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.peekaboo.Entity.Card;
import com.peekaboo.Entity.Emp;
import com.peekaboo.Entity.Order;
import com.peekaboo.Entity.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class TestPOI {

    //查询所有记录
    public static List<User> getUsers(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName("小陈_"+i);
            user.setAge(20+i);
            user.setBir(new Date());
            users.add(user);

            //身份信息
            Card card = new Card();
            card.setNo("134234342343233221");
            card.setAddress("北京市朝阳区国贸大厦3层507A");
            user.setCard(card);

            //订单信息
            List<Order> orders = new ArrayList<>();
            orders.add(new Order("12","PS5"));
            orders.add(new Order("13","Xbox series X"));
            orders.add(new Order("14","Switch"));
            user.setOrders(orders);

            if(i%2 == 0){
                user.setStatus("1");
                user.setHabbys(Arrays.asList("打篮球","看书","看片"));
            }else {
                user.setStatus("0");
                user.setHabbys(Arrays.asList("刷油管","看Twitter","刷抖音"));
            }
        }
        return users;
    }

    //导出ExcelPOI
    @Test
    public void testExport() throws IOException {

        //获取数据
        List<User> users = getUsers();
        //导出Excel
        //参数1：exportParams 导出配置对象  参数2：导出类型 参数3：导出数据集合
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息表", "用户信息表"), User.class, users);
        //创建输出流
        FileOutputStream outputStream = new FileOutputStream("E:\\StudyJava\\Excel_Output\\a2.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    //导入Excel
    @Test
    public void testImport() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);//标题占几行
        params.setHeadRows(1);//header占几行
        params.setImportFields(new String[]{"编号","状态"});

        //导出数据 餐数1:当如excel文件  参数2:导入对象的类型 参数3:导入配置对象
        List<Emp> emps = ExcelImportUtil.importExcel(new FileInputStream("E:\\StudyJava\\Excel_Output\\a2.xlsx"), Emp.class, params);
        emps.forEach(System.out::println);
        System.out.println(emps.get(1).getBir());
        Date bir = emps.get(1).getBir();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(bir));

    }
}
