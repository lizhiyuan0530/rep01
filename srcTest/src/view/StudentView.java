package view;

import dao.StudentDAO;
import model.StudentManager;
import until.MyScannerUtil;

import java.util.Scanner;

/**
 *学生操作界面
 * @author 魏建波
 * Date:  2022/8/24
 * Time:  15:58
 * @description
 */
public class StudentView {
    public static void main(String[] args) {
        // 创建一个学生管理对象    这个对象负责和页面做交互
        StudentManager wangTeacher = new StudentManager("t1001","辅导员","王老师");
        while(true){
            System.out.println("学生管理模块");
            System.out.println("请输入您的操作序号");
            System.out.println("1-添加学生");
            System.out.println("2-删除学生");
            System.out.println("3-修改学生");
            System.out.println("4-查询学生（根据学号）");
            System.out.println("5-查询学生（所有）");
            System.out.println("0-退出当前学生管理模块");
            int x = MyScannerUtil.getScannerInt();
            if(x == 0){
                break;
            }
            switch (x){
                case 1:
                    wangTeacher.addStudent();
                    break;
                case 2:
                    wangTeacher.deleteStudent();
                    break;
                case 3:
                    wangTeacher.updateStudent();
                    break;
                case 4:
                    wangTeacher.queryStudentBySno();
                    break;
                case 5:
                    wangTeacher.queryStudentAll();
                    break;
                default:
                    System.err.println("序号输入有误，请重试");
            }
        }
    }
}