package server.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@ToString(of = {"id", "lessonNumber", "lessonName", "lessonType", "lessonTopic", "teacherSurname", "classroomBuilding", "classroomCabinet", "lessonDate"})
@EqualsAndHashCode(of = {"id"})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int lessonNumber;
    private String lessonName;
    private String lessonType;
    private String lessonTopic;
    private String teacherSurname;
    private String classroomBuilding;
    private String classroomCabinet;
    private Long lessonDate;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getLessonTopic() {
        return lessonTopic;
    }

    public void setLessonTopic(String lessonTopic) {
        this.lessonTopic = lessonTopic;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getClassroomBuilding() {
        return classroomBuilding;
    }

    public void setClassroomBuilding(String classroomBuilding) {
        this.classroomBuilding = classroomBuilding;
    }

    public String getClassroomCabinet() {
        return classroomCabinet;
    }

    public void setClassroomCabinet(String classroomCabinet) {
        this.classroomCabinet = classroomCabinet;
    }

    public Long getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Long lessonDate) {
        this.lessonDate = lessonDate;
    }
}
