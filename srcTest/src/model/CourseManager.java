package model;



import dao.CourseDAO;
import until.MyScannerUtil;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/8/31
 * Time:  14:23
 * @description
 */
public class CourseManager {
    private String tno;
    private String name;
    private String job;
    // 课程集合
    private static HashSet<Course> courseHashSet = new HashSet<>();
    private static CourseDAO courseDAO = new CourseDAO();
    //  业务实现
    //添加课程
    public void addCourse(){
        System.out.println("添加课程......");
        System.out.println("请输入课程编号");
        String cno = MyScannerUtil.getScannerString();
        System.out.println("请输入课程名称");
        String name = MyScannerUtil.getScannerString();
        System.out.println("请输入课程课时");
        int hours = MyScannerUtil.getScannerInt();
        System.out.println("请输入课程学分");
        double credit = MyScannerUtil.getScannerDouble();
        System.out.println("请输入教师名称");
        String teachName = MyScannerUtil.getScannerString();
        // 组装一个对象
        Course c = new Course(cno,name,hours,credit,teachName);
        // 核心代码  把老师存到集合中
        int row = courseDAO.addCourse(c);
        if(row>0){
            System.out.println("添加成功，受影响"+row+"行");
        }else {
            System.out.println("添加失败");
        }
        System.out.println(name+"课程添加成功......");
    }
    // 根据课程号  查询课程
    public static Course findByCno(String cno){
        // 遍历集合
        // 核心代码  遍历set集合
        Course course = courseDAO.findByCno(cno);
        return course;
    }
    //修改课程
    public void updateCourse(){
        System.out.println("修改课程......");
        System.out.println("请输入要修改课程的课程号：");
        String cno = MyScannerUtil.getScannerString();
        // 根据课程查询 是否存在课程
        Course course = findByCno(cno);
        if(course == null){
            System.out.println("不存在课程号为："+cno+"的课程，请重新输入");
            updateCourse();
        }else{
            // 存在课程
            System.out.println("请输入修改课程名称");
            String name = MyScannerUtil.getScannerString();
            System.out.println("请输入修改课程课时");
            int hours = MyScannerUtil.getScannerInt();
            System.out.println("请输入修改课程学分");
            double credit = MyScannerUtil.getScannerDouble();
            System.out.println("请输入修改教师名称");
            String teachName = MyScannerUtil.getScannerString();
            // 核心代码
            Course c = new Course(cno,name,hours,credit,teachName);
            int row = courseDAO.updateCourse(c);
            if(row>0){
                System.out.println("课程修改成功");
            }else {
                System.out.println("课程修改异常");
            }
            System.out.println("课程信息修改成功!!!!");
        }
    }
    public void deleteCourse(){
        System.out.println("删除课程......");
        System.out.println("请输入要删除改课程的课程号：");
        String cno = MyScannerUtil.getScannerString();
        // 根据课程查询 是否存在课程
        Course course = findByCno(cno);
        if(course == null){
            System.out.println("不存在课程号为："+cno+"的课程，请重新输入");
            deleteCourse();
        }else{
            // 核心代码
            int row = courseDAO.deleteCourse(course);
            if(row>0){
                System.out.println("课程删除成功");
            }else {
                System.out.println("课程删除异常");
            }

        }
    }
    public void queryCourseByCno(){
        System.out.println("查询课程(根据课程号)......");
        System.out.println("请输入要查询课程的课程号：");
        String cno = MyScannerUtil.getScannerString();
        System.out.println("课程号\t课程名称\t学时\t学分\t教师姓名");
        Course course = findByCno(cno);
        if(course == null){
            System.out.println("不存在该课程！！！");
        }else{
            System.out.println(course);
        }
    }
    public Course findByTeacher(String teachName){
        // 遍历集合
        // 核心代码  遍历set集合
        Course course = courseDAO.findByTeacher(teachName);
        return course;
    }
    public void queryCourseByTeacher(){
        System.out.println("查询课程（根据教师姓名）......");
        System.out.println("请输入教师名称：");
        String teachName = MyScannerUtil.getScannerString();
        Course course = findByTeacher(teachName);
        System.out.println("课程号\t课程名称\t学时\t学分\t教师姓名");
        if(course == null){
            System.out.println("不存在该课程！！！");
        }else{
            System.out.println(course);
        }
    }
    public void queryCourseAll(){
        System.out.println("查询课程（所有）......");
        System.out.println("课程号\t课程名称\t学时\t学分\t教师姓名");
        HashSet<Course> courses = courseDAO.findAll();
        if(courses.size()==0){
            System.out.println("无课程信息");
            return;
        }
        // 核心代码  遍历set集合
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()){
            Course course = iterator.next();
            // System.out.println(course.toString());
            System.out.println(course);
        }
    }
    // getter  setter
    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}