package com.lz.excelutils.controller;

import com.alibaba.fastjson.JSONArray;
import com.lz.excelutils.entity.User;
import com.lz.excelutils.entity.User2;
import com.lz.excelutils.util.excel.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/user")
public class ExcelController {

    //导入数据
//    @PostMapping("/import")
//    public JSONArray importUser(@RequestPart("file")MultipartFile file) throws Exception {
//        JSONArray array = ExcelUtils.readMultipartFile(file);
//        System.out.println("导入数据为:" + array);
//        return array;
//    }

    //数据解析为对象
    @PostMapping("/import")
    public void importUser(@RequestPart("file") MultipartFile file) throws Exception {
        List<User> users = ExcelUtils.readMultipartFile(file, User.class);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    //导入多Sheet页
    @PostMapping("/import/sheet")
    public void upload(@RequestPart("file") MultipartFile file) throws Exception {
        Map<String, JSONArray> map = ExcelUtils.readFileManySheet(file);
        map.forEach((key, value) -> {
            System.out.println("Sheet名称：" + key);
            System.out.println("Sheet数据：" + value);
            System.out.println("----------------------");
        });
    }

    //数据导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        // 表头数据，ExcelUtils.COLUMN_MERGE：合并列
        List<Object> head = Arrays.asList("国家","姓名","年龄","性别","头像","地址",ExcelUtils.COLUMN_MERGE);
        // 用户1数据
        List<Object> user1 = new ArrayList<>();
        user1.add("蜀国");
        user1.add("诸葛亮");
        user1.add(60);
        user1.add("男");
        //导出图片
        user1.add(new URL("https://profile.csdnimg.cn/A/7/3/3_sunnyzyq"));
        user1.add("西安");
        user1.add("雁塔区");
        // 用户2数据
        List<Object> user2 = new ArrayList<>();
        user2.add("吴国");
        user2.add("大乔");
        user2.add(28);
        user2.add("女");
        //导出图片
        user2.add(new URL("https://profile.csdnimg.cn/6/1/9/0_m0_48717371"));
        user2.add("西安");
        user2.add("莲湖区");
        // 用户3数据
        List<Object> user3 = new ArrayList<>();
        //ExcelUtils.ROW_MERGE：合并行
        user3.add(ExcelUtils.ROW_MERGE);
        user3.add("小乔");
        user3.add(28);
        user3.add("女");
        //导出图片
        user3.add(new URL("https://profile.csdnimg.cn/6/1/9/0_m0_48717371"));
        user3.add("西安");
        user3.add("莲湖区");
        // 将数据汇总
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        sheetDataList.add(user1);
        sheetDataList.add(user2);
        sheetDataList.add(user3);

        //设置下拉列表
        Map<Integer, List<String>> selectMap = new HashMap<>();
        //key为第几列，从0开始，值为下拉数据
        selectMap.put(2, Arrays.asList("男","女"));

        // 导出数据
        ExcelUtils.export(response,"用户表", sheetDataList, selectMap);
    }

    //导出模板
    @GetMapping("/export/template")
    public void exportTemplate(HttpServletResponse response){
        ExcelUtils.exportTemplate(response, "用户表", User2.class);
    }

    //导出模板，带示例数据
    @GetMapping("/export/template2")
    public void exportTemplate2(HttpServletResponse response){
        ExcelUtils.exportTemplate(response, "用户表", User2.class, true);
    }

    //数据导出，按对象导出数据
    @GetMapping("/export/object")
    public void exportObject(HttpServletResponse response){
        //构建数据
        List<User2> user2s = new ArrayList<>();
        User2 user = new User2();
        user.setName("张三");
        user.setAge(20);
        user.setSex("1");
        user.setTel("12345");
        user.setCity("西安");
        user2s.add(user);

        User2 user2 = new User2();
        user2.setName("李四");
        user2.setAge(22);
        user2.setSex("2");
        user2.setTel("11111");
        user2.setCity("西安");
        user2s.add(user2);

        ExcelUtils.export(response, "用户表", user2s, User2.class);
    }
}
