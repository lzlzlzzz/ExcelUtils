package com.lz.excelutils.entity;

import com.lz.excelutils.util.excel.ExcelImport;
import lombok.Data;

@Data
public class User {

    //excel行号
    private int rowNum;

    //required：限制字段必填，unique：限制字段唯一，设置多个即为联合唯一，单个字段相同可以校验通过
    @ExcelImport(value = "姓名",required = true,unique = true)
    private String name;

    @ExcelImport("年龄")
    private Integer age;

    //字段自动映射
    @ExcelImport(value = "性别",kv = "1-男;2-女")
    private String sex;

    //限制最大长度
    @ExcelImport(value = "电话", maxLength = 11)
    private String tel;

    @ExcelImport("城市")
    private String city;

    //原始数据
    private String rowData;

    //错误提示
    private String rowTips;
}
