package com.zelin.web.pojo;

public class Student {
    private int sid;
    private String sname;
    private String sex;
    private int age;
    private String addr;
    private int cid;
    private String cname;

    public Student() {
    }

    public Student(String sname, String sex, int age, String addr) {
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
        this.cid = cid;
    }

    public Student(int sid, String sname, String sex, int age, String addr) {
        this.sid = sid;
        this.sname = sname;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
