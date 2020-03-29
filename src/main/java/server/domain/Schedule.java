package server.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity

@ToString(of = {"id", "lesson", "topic", "dateSchedule", "numberLesson", "surnameTeacher", "nameTeacher", "patronymicTeacher", "buildingClassroom", "cabinetClassroom"})
@EqualsAndHashCode(of = {"id"})
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lesson;
    private String typeLesson;
    private String topic;
    private Long dateSchedule;
    private String numberLesson;
    private String surnameTeacher;
    private String nameTeacher;
    private String patronymicTeacher;
    private String buildingClassroom;
    private String cabinetClassroom;

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTypeLesson() {
        return typeLesson;
    }

    public void setTypeLesson(String typeLesson) {
        this.typeLesson = typeLesson;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Long dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public String getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(String numberLesson) {
        this.numberLesson = numberLesson;
    }

    public String getSurnameTeacher() {
        return surnameTeacher;
    }

    public void setSurnameTeacher(String surnameTeacher) {
        this.surnameTeacher = surnameTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getPatronymicTeacher() {
        return patronymicTeacher;
    }

    public void setPatronymicTeacher(String patronymicTeacher) {
        this.patronymicTeacher = patronymicTeacher;
    }

    public String getBuildingClassroom() {
        return buildingClassroom;
    }

    public void setBuildingClassroom(String buildingClassroom) {
        this.buildingClassroom = buildingClassroom;
    }

    public String getCabinetClassroom() {
        return cabinetClassroom;
    }

    public void setCabinetClassroom(String cabinetClassroom) {
        this.cabinetClassroom = cabinetClassroom;
    }
}
