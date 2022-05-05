package com.lz.excelutils.controller;

import com.alibaba.fastjson.JSONArray;
import com.lz.excelutils.entity.User;
import com.lz.excelutils.util.excel.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        // 表头数据
        List<Object> head = Arrays.asList("姓名","年龄","性别","头像");
        // 用户1数据
        List<Object> user1 = new ArrayList<>();
        user1.add("诸葛亮");
        user1.add(60);
        user1.add("男");
        //导出图片
        user1.add(new URL("https://profile.csdnimg.cn/A/7/3/3_sunnyzyq"));
        // 用户2数据
        List<Object> user2 = new ArrayList<>();
        user2.add("大乔");
        user2.add(28);
        user2.add("女");
        //导出图片
        user2.add(new URL("https://profile.csdnimg.cn/6/1/9/0_m0_48717371"));
        // 将数据汇总
        List<List<Object>> sheetDataList = new ArrayList<>();
        sheetDataList.add(head);
        sheetDataList.add(user1);
        sheetDataList.add(user2);
        // 导出数据
        ExcelUtils.export(response,"用户表", sheetDataList);
    }
}
