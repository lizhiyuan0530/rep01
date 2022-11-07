package model;


import dao.CourseDAO;
import dao.StudentCourseDao;
import dao.StudentDAO;
import until.MyScannerUtil;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/9/2
 * Time:  15:06
 * @description
 */
public class StudentCourseManager {
    //定义StudentCourse对应的DAO包
    private StudentCourseDao studentCourseDao = new StudentCourseDao();

    //定义StudentDAO包
    private StudentDAO studentDAO = new StudentDAO();
    //定义CourseDAO包
    private CourseDAO courseDAO = new CourseDAO();
    // 对选课集合做管理  Map
    private static HashMap<String,StudentCourse> studentCourseHashMap = new HashMap<>();

    public void selectCourse(){
        System.out.println("学生选课【学号 课程号】");
        System.out.println("请输入选课学生的学号？");
        String sno = MyScannerUtil.getScannerString();
        // 验证  你的学号是否存在
        Student student = studentDAO.findBySno(sno);
        if(student == null){
            System.out.println("学号输入有误，请重试！！！");
            selectCourse();
        } else{
            Course course = null;
            String cno = null;
            do{
                System.out.println("请输入选课学生的课程号？");
                 cno = MyScannerUtil.getScannerString();
                // 验证 课程号是否存在
                course = courseDAO.findByCno(cno);
            }while (course == null);
            // 程序继续执行
            // 构建一个选课信息
            StudentCourse studentCourse = new StudentCourse();
            // 把学生和课程设置到选课对象
            studentCourse.setStudent(student);
            studentCourse.setCourse(course);
            //todo 添加之前 需要验证不能重复添加课程
                //为真 开始添加选课
            boolean flag = studentCourseDao.isExistStudentCourse(sno, cno);
            if(flag){
                int row = studentCourseDao.selectCourse(sno, cno);
                if(row>0){
                    System.out.println("选课成功");
                }else if(row == 0){
                    System.out.println("选课异常");
                }
            }else {
                System.out.println("该课已选，请重试");
                selectCourse();
            }


        }
    }

    // 根据学号 课程号，退选课程
    public void exitCourse(){
        System.out.println("学生退课【学号 课程号】");
        // 输入学号
        System.out.println("请输入退课学生的学号：");
        String sno = MyScannerUtil.getScannerString();
        //  是否存在该学生
        Student student = studentDAO.findBySno(sno);
        while (student == null){
            System.out.println("输入的学号有误，请重新输入：");
            sno = MyScannerUtil.getScannerString();
            student = studentDAO.findBySno(sno);
        }
        // 该学生选了哪些课程
        // 输入课程号
        System.out.println("请输入退课管理的课程号：");
        String cno = MyScannerUtil.getScannerString();
        Course course = courseDAO.findByCno(cno);
        while (course == null){
            System.out.println("输入的课程号有误，请重新输入：");
            cno = MyScannerUtil.getScannerString();
            course = courseDAO.findByCno(cno);
        }
        // 验证课程号是否在选课信息里
        boolean flag = studentCourseDao.isExistStudentCourse(sno, cno);
        while(flag){
            System.out.println("该学生没有选择该课程，请重试：");
            cno = MyScannerUtil.getScannerString();
            flag = studentCourseDao.isExistStudentCourse(sno, cno);
        }
        int row = studentCourseDao.deleteCourse(sno, cno);
        // 可以推选  核心代码
        if(row>0){
            System.out.println("退课成功");
        }else if(row == 0){
            System.out.println("退课异常");
        }
    }

    //  查找学生选课信息对应的key
    public String findKey(StudentCourse studentCourse){
        // key
        Set<String> keys = studentCourseHashMap.keySet();
        for(Iterator<String> iterator = keys.iterator();iterator.hasNext();){
            String key = iterator.next();
            // 根据可以 获得值
            StudentCourse sc = studentCourseHashMap.get(key);
            if(sc == studentCourse){
                return key;
            }

        }
        return null;
    }

    // 根据课程号查询选课信息
    public StudentCourse queryStudentCourseByCon(String cno){
        // 获得map中的values
        Collection<StudentCourse> studentCourseCollection = studentCourseHashMap.values();
        // 迭代器去遍历
        for(Iterator<StudentCourse> iterator = studentCourseCollection.iterator();iterator.hasNext();){
            StudentCourse studentCourse = iterator.next();
            if(cno.equals(studentCourse.getCourse().getCno())){
               return studentCourse;
            }
        }
        return null;
    }
    // 根据学生信息查询选课信息
    public void queryStudentCourseBySno(Student student){
        System.out.println(student.getName()+"学生的选课信息如下：");
        System.out.println("课程号\t课程名称\t学时\t学分\t教师姓名");
        // 遍历选课信息
        // 获得map中的values
        Collection<StudentCourse> studentCourseCollection = studentCourseHashMap.values();
        // 迭代器去遍历
        for(Iterator<StudentCourse> iterator = studentCourseCollection.iterator();iterator.hasNext();){
            StudentCourse studentCourse = iterator.next();
            if(student == studentCourse.getStudent()){
                Course course = studentCourse.getCourse();
                System.out.println(course);
            }
        }

    }
    public void queryStudentCourseByCname(){
        System.out.println("查询选课信息（按照课程名称）");
        System.out.println("请输入课程名称");
        String name = "%"+MyScannerUtil.getScannerString()+"%";
        // 遍历值
        System.out.println("序号\t学号\t姓名\t性别\t年龄\t课程号\t课程名称\t学时\t学分\t教师姓名");
        // 获得map中的values
        HashMap<String, StudentCourse> map = studentCourseDao.queryStudentCourseByCname(name);
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            StudentCourse studentCourse = map.get(key);
            Student s = studentCourse.getStudent();
            System.out.print(key+"\t"+s.getSno()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t");//不换行
            Course c = studentCourse.getCourse();
            System.out.println(c.getCno()+"\t"+c.getName()+"\t"+c.getHours()+"\t"+c.getCredit()+"\t"+c.getTeachName());
        }

    }
    public void queryStudentCourseBySno(){
        System.out.println("查询选课信息（按照学号）");
        System.out.println("请输入学号");
        String sno = MyScannerUtil.getScannerString();
        // 遍历值
        System.out.println("序号\t学号\t姓名\t性别\t年龄\t课程号\t课程名称\t学时\t学分\t教师姓名");
        // 获得map中的values
        HashMap<String, StudentCourse> map = studentCourseDao.queryStudentCourseBySno(sno);
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key =  iterator.next();
            StudentCourse studentCourse = map.get(key);
            Student s = studentCourse.getStudent();
            System.out.print(key+"\t"+s.getSno()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t");//不换行
            Course c = studentCourse.getCourse();
            System.out.println(c.getCno()+"\t"+c.getName()+"\t"+c.getHours()+"\t"+c.getCredit()+"\t"+c.getTeachName());
        }
        // 迭代器去遍历


    }

    public void queryStudentCourseAll(){
        System.out.println("查询所有选课信息");
        // 遍历
        // 迭代器去处理
        HashMap<String, StudentCourse> map = studentCourseDao.findAll();
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        System.out.println("序号\t学号\t姓名\t性别\t年龄\t课程号\t课程名称\t学时\t学分\t教师姓名");
        while(iterator.hasNext()){
            String key = iterator.next();
            StudentCourse studentCourse = map.get(key);
            Student s = studentCourse.getStudent();
            System.out.print(key+"\t"+s.getSno()+"\t"+s.getName()+"\t"+s.getSex()+"\t"+s.getAge()+"\t");//不换行
            Course c = studentCourse.getCourse();
            System.out.println(c.getCno()+"\t"+c.getName()+"\t"+c.getHours()+"\t"+c.getCredit()+"\t"+c.getTeachName());
        }
    }
}