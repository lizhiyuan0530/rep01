package model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/8/24
 * Time:  15:51
 * @description
 */
public class Student {
    /*
    学生类型
        成员变量：学号  姓名  性别  年龄
        方法：getter  setter
     */
    private String sno;
    private String name;
    private String sex;
    private int age;
    public Student(){}
    public Student(String sno, String name, String sex, int age) {
        this.sno = sno;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}