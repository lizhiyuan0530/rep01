package model;

/**
 * 选课信息
 *   学生信息   课程信息
 *
 * @author 魏建波
 * Date:  2022/9/2
 * Time:  15:03
 * @description
 */
public class StudentCourse {
    private Student student;
    private Course course;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}