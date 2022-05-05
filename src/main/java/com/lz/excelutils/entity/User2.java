package com.lz.excelutils.entity;

import com.lz.excelutils.util.excel.ExcelExport;
import lombok.Data;

@Data
public class User2 {

    //sort：调整表头顺序，值越小越靠前
    @ExcelExport(value = "姓名", example = "张三", sort = 1)
    private String name;

    @ExcelExport(value = "年龄", example = "20")
    private Integer age;

    //kv = "1-男;2-女"：设置属性值映射
    @ExcelExport(value = "性别", example = "男", kv = "1-男;2-女")
    private String sex;

    @ExcelExport(value = "电话", example = "123456")
    private String tel;

    @ExcelExport(value = "城市", example = "西安")
    private String city;
}
