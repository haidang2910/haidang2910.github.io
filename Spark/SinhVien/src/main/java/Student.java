
import java.io.Serializable;

public class Student implements Serializable {
    private int ID;
    private Double Grade;
    private String Subject;

    public Student(int ID, Double grade, String subject) {
        this.ID = ID;
        Grade = grade;
        Subject = subject;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Double getGrade() {
        return Grade;
    }

    public void setGrade(Double grade) {
        Grade = grade;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + ID +
                ", Grade=" + Grade +
                ", Subject='" + Subject + '\'' +
                '}';
    }
}
