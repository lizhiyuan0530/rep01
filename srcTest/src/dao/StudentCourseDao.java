package dao;

import model.Course;
import model.Student;
import model.StudentCourse;
import until.DataSourceUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class StudentCourseDao {

    //添加选课
    public int selectCourse(String sno ,String cno){
        Connection connection = DataSourceUntil.getConnection();
        //查询是否已经存在
        String sql = "insert into studentcourse(sno, cno) value (?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ps.setString(2,cno);
            int row = ps.executeUpdate();
            return row;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    //选课退选
    public int deleteCourse(String sno,String cno){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "delete from studentcourse where sno=?and cno=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ps.setString(2,cno);
            int row = ps.executeUpdate();
            return row;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    //判断是否存在 存在为假，不存在为真
    public boolean isExistStudentCourse(String sno ,String cno){
        Connection connection = DataSourceUntil.getConnection();
        //查询是否已经存在
        String sql = "select * from studentcourse where sno =? and cno =?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ps.setString(2,cno);
            ResultSet set = ps.executeQuery();
            if(set.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;

    }

    //根据课程名查询
    public HashMap<String, StudentCourse>queryStudentCourseByCname(String name){
        Connection connection = DataSourceUntil.getConnection();
        HashMap<String, StudentCourse> map = new HashMap<>();
        String sql="select s.sno, s.`name`, s.sex, s.age, c.cno,c.name, c.hours, c.credit, c.teacherName from students s, courses c, studentcourse sc where sc.sno= s.sno and sc.cno= c.cno and c.name like ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String sno = set.getString(1);
                String sName = set.getString(2);
                String sex = set.getString(3);
                int age = set.getInt(4);
                String cno = set.getString(5);
                String cName = set.getString(6);
                int hours = set.getInt(7);
                double credit = set.getDouble(8);
                String teacherName = set.getString(9);
                Student student = new Student(sno,sName,sex,age);
                Course course = new Course(cno,cName,hours,credit,teacherName);
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudent(student);
                studentCourse.setCourse(course);
                int length = map.size()+1;
                map.put(length+"",studentCourse);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    //根据学号查询
    public HashMap<String, StudentCourse>queryStudentCourseBySno(String sno){
        Connection connection = DataSourceUntil.getConnection();
        HashMap<String, StudentCourse> map = new HashMap<>();
        String sql="select s.sno, s.`name`, s.sex, s.age, c.cno,c.name, c.hours, c.credit, c.teacherName from students s, courses c, studentcourse sc where sc.sno= s.sno and sc.cno= c.cno and s.sno=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String sName = set.getString(2);
                String sex = set.getString(3);
                int age = set.getInt(4);
                String cno = set.getString(5);
                String cName = set.getString(6);
                int hours = set.getInt(7);
                double credit = set.getDouble(8);
                String teacherName = set.getString(9);
                Student student = new Student(sno,sName,sex,age);
                Course course = new Course(cno,cName,hours,credit,teacherName);
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudent(student);
                studentCourse.setCourse(course);
                int length = map.size()+1;
                map.put(length+"",studentCourse);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return map;
    }

    //查询所有选课信息
    public HashMap<String, StudentCourse> findAll(){
        Connection connection = DataSourceUntil.getConnection();
        HashMap<String, StudentCourse> map = new HashMap<>();
        String sql="select s.sno, s.`name`, s.sex, s.age, c.cno, c.`name`, c.hours, c.credit, c.teacherName from students s, courses c, studentcourse sc where sc.sno= s.sno and sc.cno= c.cno ";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String sno = set.getString(1);
                String sName = set.getString(2);
                String sex = set.getString(3);
                int age = set.getInt(4);
                String cno = set.getString(5);
                String cName = set.getString(6);
                int hours = set.getInt(7);
                double credit = set.getDouble(8);
                String teacherName = set.getString(9);
                Student student = new Student(sno,sName,sex,age);
                Course course = new Course(cno,cName,hours,credit,teacherName);
                StudentCourse studentCourse = new StudentCourse();
                studentCourse.setStudent(student);
                studentCourse.setCourse(course);
                int length = map.size()+1;
                map.put(length+"",studentCourse);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return map;
    }
}
