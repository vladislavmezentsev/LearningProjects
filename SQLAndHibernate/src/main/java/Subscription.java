import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private Key id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private static Date subscriptionDate;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public static void setSubscriptionDate(Date subscriptionDate) {
        Subscription.subscriptionDate = subscriptionDate;
    }

}
