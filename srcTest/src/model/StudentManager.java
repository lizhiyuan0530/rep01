package model;



import dao.StudentDAO;
import until.MyScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/8/24
 * Time:  15:53
 * @description
 */
public class StudentManager {
    /*
    管理学生类型（宿舍楼下门卫   辅导员）
        成员变量：工号  职位  姓名  学生集合
        方法：向学生集合去添加学生、修改学生、删除学生、查询学生（根据学号，查询所有）
     */
    // 该数据不需要对外暴露
    // 初始化学生DAO
    private static StudentDAO studentDAO = new StudentDAO();

    // 方法
    public void addStudent(){
        System.out.println("执行学生添加功能");
        System.out.println("请输入学号：");
        String sno = MyScannerUtil.getScannerString();
        System.out.println("请输入姓名：");
        String name = MyScannerUtil.getScannerString();
        System.out.println("请输入性别：");
        String sex = MyScannerUtil.getScannerString();
        System.out.println("请输入年龄：");
        int age = MyScannerUtil.getScannerInt();

        // 组装成一个学生对象
        Student s = new Student(sno,name,sex,age);
        // 调用集合的add方法
        int row = studentDAO.addStudent(s);
        if(row>0){
            System.out.println("添加成功，受影响"+row+"行");
        }else {
            System.out.println("添加失败");
        }
    }
    // 验证是否存在某个学号的学生
    public boolean isExistSno(String sno){

        boolean flag = studentDAO.isExistSno(sno);
        return flag;
    }
    // 根据学号查询学生
    public static Student findBySno(String sno){
        // 遍历学生集合  一个一个对学号
        Student s = studentDAO.findBySno(sno);
        return s;
    }
    public void deleteStudent(){
        System.out.println("执行学生删除功能");
        // 输入学号  进行删除
        System.out.println("请输入要删除的学生学号");
        // 验证   学号是否存在  如果不存在需要重新输入  或者程序不去执行任何的操作
        String sno = MyScannerUtil.getScannerString();
        // 验证是否存在
        boolean isExist = isExistSno(sno);
        if(isExist == true){
            // 可以正常走业务  删除  获得到对象 然后直接删除了
            // 获得到该对象
            Student s = findBySno(sno);
            int row = studentDAO.deleteStudent(s);
            if(row>0){
                System.out.println("学生删除成功");
            }else {
                System.out.println("学生删除异常");
            }
        }else{
            // 重复进行输入
            System.out.println("学号不存在，请重新输入");
            // 递归
            deleteStudent();  // 自己调用自己
        }
    }
    public void updateStudent(){
        System.out.println("执行学生修改功能");
        // 输入学号  进行删除
        System.out.println("请输入要修改的学生学号");
        // 验证   学号是否存在  如果不存在需要重新输入  或者程序不去执行任何的操作
        String sno = MyScannerUtil.getScannerString();
        // 验证是否存在
        boolean isExist = isExistSno(sno);
        if(isExist == true){
            // 可以正常走业务  删除  获得到对象 然后直接删除了
            // 获得到该对象
            Student s = findBySno(sno);
            // 执行修改
            System.out.println("请输入修改学生的姓名");
            String name = MyScannerUtil.getScannerString();
            System.out.println("请输入修改学生的性别：");
            String sex = MyScannerUtil.getScannerString();
            System.out.println("请输入修改学生的年龄：");
            int age = MyScannerUtil.getScannerInt();
            // 把值赋给student对象
            Student student = new Student(sno,name,sex,age);
            //传入到数据库
            int row = studentDAO.updateStudent(student);
            if(row>0){
                System.out.println("学生修改成功");
            }else {
                System.out.println("学生修改异常");
            }
        }else{
            // 重复进行输入
            System.out.println("学号不存在，请重新输入");
            // 递归
            updateStudent();  // 自己调用自己
        }
    }
    public void queryStudentBySno(){
        System.out.println("执行查询学生（根据学号）");
        System.out.println("请输入查询学生的学号");
        String sno = MyScannerUtil.getScannerString();
        Student s = findBySno(sno);
        if(s == null){
            System.out.println("学号输入有误，请重试！！！");
            queryStudentBySno();
        }else{
            System.out.println("学号\t姓名\t性别\t年龄");
            System.out.println(s.getSno()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge());
        }
    }
    public void queryStudentAll(){
        System.out.println("执行查询学生（所有）");
        System.out.println("学号\t姓名\t性别\t年龄");
        // 遍历集合
        ArrayList<Student> studentList = studentDAO.findAll();
        if(studentList.size() == 0){
            System.out.println("暂时没有存储学生信息");
            return; // 结束函数
        }
        // 遍历学生信息
        for(int i=0;i<studentList.size();i++){
            Student s = studentList.get(i);
            System.out.println(s.getSno()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge());
        }
    }
    private String tno;
    private String job;
    private String name;
    public StudentManager(){}
    public StudentManager(String tno, String job, String name) {
        this.tno = tno;
        this.job = job;
        this.name = name;
    }
    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}