package view;


import model.CourseManager;
import until.MyScannerUtil;

/**
 *课程操作界面
 * @author 魏建波
 * Date:  2022/8/24
 * Time:  15:58
 * @description
 */
public class CourseView {
    public static void main(String[] args) {
        // 创建一个课程管理对象    这个对象负责和页面做交互
        CourseManager courseManager = new CourseManager();
        while(true){
            System.out.println("课程管理模块");
            System.out.println("请输入您的操作序号");
            System.out.println("1-添加课程");
            System.out.println("2-删除课程");
            System.out.println("3-修改课程");
            System.out.println("4-查询课程（根据课程号）");
            System.out.println("5-查询课程（根据教师名）");
            System.out.println("6-查询课程（所有）");
            System.out.println("0-退出当前课程管理模块");
            int x = MyScannerUtil.getScannerInt();
            if(x == 0){
                break;
            }
            switch (x){
                case 1:
                    courseManager.addCourse();
                    break;
                case 2:
                    courseManager.deleteCourse();
                    break;
                case 3:
                    courseManager.updateCourse();
                    break;
                case 4:
                    courseManager.queryCourseByCno();
                    break;
                case 5:
                    courseManager.queryCourseByTeacher();
                    break;
                case 6:
                    courseManager.queryCourseAll();
                    break;
                default:
                    System.err.println("序号输入有误，请重试");
            }
        }
    }
}