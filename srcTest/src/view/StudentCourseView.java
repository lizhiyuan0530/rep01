package view;


import model.StudentCourseManager;
import until.MyScannerUtil;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/9/2
 * Time:  15:11
 * @description
 */
public class StudentCourseView {
    public static void main(String[] args) {
        // 创建一个选课管理对象    这个对象负责和页面做交互
        StudentCourseManager studentCourseManager = new StudentCourseManager();
        while(true){
            System.out.println("选课管理模块");
            System.out.println("请输入您的操作序号");
            System.out.println("1-学生选课【学号 课程号】");
            System.out.println("2-学生退课【学号 课程号】");
            System.out.println("3-查询选课信息（按照课程名称）");
            System.out.println("4-查询选课信息（按照学号）");
            System.out.println("5-查询所有选课信息");
            System.out.println("0-退出当前选课管理模块");
            int x = MyScannerUtil.getScannerInt();
            if(x == 0){
                break;
            }
            switch (x){
                case 1:
                    studentCourseManager.selectCourse();
                    break;
                case 2:
                    studentCourseManager.exitCourse();
                    break;
                case 3:
                    studentCourseManager.queryStudentCourseByCname();
                    break;
                case 4:
                    studentCourseManager.queryStudentCourseBySno();
                    break;
                case 5:
                    studentCourseManager.queryStudentCourseAll();
                    break;
                default:
                    System.err.println("序号输入有误，请重试");
            }
        }
    }
}