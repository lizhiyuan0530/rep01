package dao;

import model.Course;
import model.Student;
import until.DataSourceUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class CourseDAO {
    //添加课程方法
    public int addCourse(Course course){
        //获取连接
        Connection connection = DataSourceUntil.getConnection();

        String sql = "insert into courses(cno,name,hours,credit,teacherName) values(?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, course.getCno());
            ps.setString(2,course.getName());
            ps.setInt(3,course.getHours());
            ps.setDouble(4,course.getCredit());
            ps.setString(5,course.getTeachName());
            //执行sql
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

    //修改课程信息
    public int updateCourse(Course course){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "update courses set name=?,hours=?,credit=?,teacherName=? where cno=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, course.getName());
            ps.setInt(2,course.getHours());
            ps.setDouble(3,course.getCredit());
            ps.setString(4, course.getTeachName());
            ps.setString(5, course.getCno());
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

    //删除课程信息
    public int deleteCourse(Course course){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "delete from courses where cno = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,course.getCno());
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


    //根据课程号查找课程
    public Course findByCno(String cno){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "select cno,name,hours,credit,teacherName from courses where cno=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,cno);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String name = set.getString("name");
                int hours = set.getInt("hours");
                double credit = set.getDouble("credit");
                String teacherName = set.getString("teacherName");
                Course c = new Course(cno, name, hours, credit, teacherName);
                return c;
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
        return null;
    }

    //根据教师名查询课程
    public Course findByTeacher(String teachName){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "select cno,name,hours,credit from courses where teacherName=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,teachName);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String cno = set.getString("cno");
                String name = set.getString("name");
                int hours = set.getInt("hours");
                double credit = set.getDouble("credit");
                Course c = new Course(cno, name, hours, credit, teachName);
                return c;
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
        return null;
    }

    //查询所有课程
    public HashSet<Course> findAll(){
        //获取连接
        Connection connection = DataSourceUntil.getConnection();

        HashSet<Course> courses = new HashSet<>();
        String sql = "select cno,`name`, hours,credit,teacherName from courses";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String cno = set.getString(1);
                String name = set.getString(2);
                int hours = set.getInt(3);
                double credit = set.getDouble(4);
                String teachName = set.getString(5);
                Course course = new Course(cno, name, hours, credit, teachName);
                courses.add(course);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return courses;
    }
}
