package view;


import until.MyScannerUtil;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/9/7
 * Time:  16:12
 * @description
 */
public class MainView {
    public static void main(String[] args) {
        while(true){
            System.out.println("欢迎使用学生综合管理平台");
            System.out.println("1-学生管理");
            System.out.println("2-课程管理");
            System.out.println("3-选课管理");
            System.out.println("0-安全退出");
            int key = MyScannerUtil.getScannerInt();
            if(key==0){
                break;
            }
            switch (key){
                case 1:
                    StudentView.main(args);
                    break;
                case 2:
                    CourseView.main(args);
                    break;
                case 3:
                    StudentCourseView.main(args);
                    break;
            }
        }
    }
}