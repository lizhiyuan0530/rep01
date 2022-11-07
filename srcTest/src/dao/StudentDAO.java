package dao;

import model.Student;
import until.DataSourceUntil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {


    //student表插入方法
    public int addStudent(Student student){
        //获取连接
        Connection connection = DataSourceUntil.getConnection();

        String sql = "insert into students(sno,name,sex,age) values(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, student.getSno());
            ps.setString(2,student.getName());
            ps.setString(3,student.getSex());
            ps.setInt(4,student.getAge());
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

    //删除学生
    public int deleteStudent(Student s){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "delete from students where sno = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,s.getSno());
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

    //修改学生信息
    public int updateStudent(Student student){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "update students set name=?,sex=?,age=? ,age=30 where sno=?";
        PreparedStatement ps=null;
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(4,student.getSno());
            ps.setString(1,student.getName());
            ps.setString(2,student.getSex());
            ps.setInt(3,student.getAge());
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

    //查询所有学生
    public ArrayList<Student> findAll(){
        //获取连接
        Connection connection = DataSourceUntil.getConnection();

        ArrayList<Student> studentArrayList = new ArrayList<>();
        String sql = "select sno,`name`, sex,age from students";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet set = ps.executeQuery();
            while (set.next()){
                String sno = set.getString(1);
                String name = set.getString(2);
                String sex = set.getString(3);
                int age = set.getInt(4);
                Student student = new Student(sno,name,sex,age);
                studentArrayList.add(student);
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
        return studentArrayList;
    }

    //根据学号查询学生是否存在
    public boolean isExistSno(String sno){
        Connection connection = DataSourceUntil.getConnection();
        String sql = "select name from students where sno =?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ResultSet set = ps.executeQuery();
            if(set.next()){
                return true;
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
        return false;
    }

    //根据学号查询学生
    public Student findBySno(String sno){
        Connection connection = DataSourceUntil.getConnection();
        String sql="select sno,name,sex,age from students where sno = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,sno);
            ResultSet set = ps.executeQuery();
            if(set.next()){
                String name = set.getString("name");
                String sex = set.getString("sex");
                int age = set.getInt("age");
                Student s = new Student(sno,name,sex,age);
                return s;
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
}

