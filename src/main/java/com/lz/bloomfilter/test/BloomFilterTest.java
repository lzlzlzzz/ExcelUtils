package com.lz.bloomfilter.test;

import com.lz.bloomfilter.filter.MyBloomFilter;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class BloomFilterTest {

    private static Random random = new Random();
    private static String[] names = {"黄某人", "负债程序猿", "谭sir", "郭德纲", "李狗蛋", "铁蛋", "赵铁柱", "蔡徐鸡", "蔡徐母鸡"};
    private static String[] addrs = {"二仙桥", "成华大道", "春熙路", "锦里", "宽窄巷子", "双子塔", "天府大道", "软件园", "熊猫大道", "交子大道"};
    private static String[] companys = {"京东", "腾讯", "百度", "小米", "米哈游", "网易", "字节跳动", "美团", "蚂蚁", "完美世界"};
    private static LocalDateTime now = LocalDateTime.now();

    public static void main(String[] args) {
        //实例化
        MyBloomFilter filter = new MyBloomFilter();
        for (int i = 0; i < 20; i++) {
            //push到BloomFilter
            getPersonList(500000).forEach(person -> filter.push(person));
        }
        //push一个确定的对象
        filter.push(getFixedPerson(now));
        //判断这个对象是否存在
        long start = System.currentTimeMillis();
        System.out.println(filter.contain(getFixedPerson(now)));
        long end = System.currentTimeMillis() - start;
        System.out.println("bloomFilter内对象数量：" + filter.getCurrentBeanCount());
        System.out.println("耗时(ms)：" + end + ",消耗内存(m)：" + (ObjectSizeCalculator.getObjectSize(filter) / (1024 * 1024)));
    }

    //获取指定数量person
    private static ArrayList<Person> getPersonList(int count) {
        ArrayList<Person> persons = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            persons.add(getPerson());
        }
        return persons;
    }

    //获得一个确定的person，需传入一个date，什么作用这里先别管，后面一看就懂
    private static Person getFixedPerson(LocalDateTime date) {
        Person person = new Person.Builder()
                .name("薇娅")
                .phone(18966666666L)
                .salary(new BigDecimal(99999))
                .company("天猫")
                .ifSingle(0)
                .sex(0)
                .address("上海市徐家汇某栋写字楼")
                .createTime(date)
                .createUser("老马").build();
        return person;
    }

    //随机生成person
    private static Person getPerson() {
        Person person = new Person.Builder()
                .name(names[random.nextInt(names.length)])
                .phone(18800000000L + random.nextInt(88888888))
                .salary(new BigDecimal(random.nextInt(99999)))
                .company(companys[random.nextInt(companys.length)])
                .ifSingle(random.nextInt(2))
                .sex(random.nextInt(2))
                .address("四川省成都市" + addrs[random.nextInt(addrs.length)])
                .createTime(LocalDateTime.now())
                .createUser(names[random.nextInt(names.length)]).build();
        return person;
    }
}
