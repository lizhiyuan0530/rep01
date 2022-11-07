package model;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 魏建波
 * Date:  2022/8/26
 * Time:  15:47
 * @description
 */
public class Course {

    @Override
    public String toString() {
        return cno+"\t"+name+"\t"+hours+"\t"+credit+"\t"+teachName;
    }

    @Override
    public int hashCode() {
        // System.out.println("调用hashcode....................");
        return 1;
    }

    @Override
    public boolean equals(Object obj) {  // jdk hashSet内部实现  保证唯一  hashcode   equals
        // System.out.println("调用equals....................");
        if(obj instanceof Course){
            // 可以去验证是否内容相等
            Course c =(Course) obj;
            if(cno.equals(c.cno) && name.equals(c.name) && hours == c.hours && credit == c.credit && teachName.equals(c.teachName)){
                return true;
            }
        }
        return false;
    }

    private String cno;
    private String name;
    private int hours;
    private double credit;
    private String teachName;

    public Course(String cno, String name, int hours, double credit, String teachName) {
        this.cno = cno;
        this.name = name;
        this.hours = hours;
        this.credit = credit;
        this.teachName = teachName;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }
}