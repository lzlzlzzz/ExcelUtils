package com.lz.bloomfilter.test;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Person {
    private Long id;
    private String name;//姓名
    private Long phone;//电话
    private BigDecimal salary;//薪水
    private String company;//公司
    private Integer ifSingle;//是否单身
    private Integer sex;//性别
    private String address;//住址
    private LocalDateTime createTime;
    private String createUser;

    public Person(Builder builder) {
        this.name = builder.name;
        this.phone = builder.phone;
        this.salary = builder.salary;
        this.company = builder.company;
        this.ifSingle = builder.ifSingle;
        this.sex = builder.sex;
        this.address = builder.address;
        this.createTime = builder.createTime;
        this.createUser = builder.createUser;
    }

    public static class Builder{
        private String name;//姓名
        private Long phone;//电话
        private BigDecimal salary;//薪水
        private String company;//公司
        private Integer ifSingle;//是否单身
        private Integer sex;//性别
        private String address;//住址
        private LocalDateTime createTime;
        private String createUser;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder phone(Long phone){
            this.phone = phone;
            return this;
        }

        public Builder salary(BigDecimal salary){
            this.salary = salary;
            return this;
        }

        public Builder company(String company){
            this.company = company;
            return this;
        }

        public Builder ifSingle(Integer ifSingle){
            this.ifSingle = ifSingle;
            return this;
        }

        public Builder sex(Integer sex){
            this.sex = sex;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder createTime(LocalDateTime createTime){
            this.createTime = createTime;
            return this;
        }

        public Builder createUser(String createUser){
            this.createUser = createUser;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }
}
