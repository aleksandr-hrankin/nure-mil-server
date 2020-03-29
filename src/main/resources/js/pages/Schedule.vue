<template>
    <div>
        <table>
            <tr>
                <th>№</th>
                <th>Номер</th>
                <th>Назва</th>
                <th>Тип</th>
                <th>Тема</th>
                <th>Викладач</th>
                <th>Корпус</th>
                <th>Аудиторія</th>
                <th>Дата</th>
                <th></th>
                <th></th>
            </tr>

            <schedule-tr v-for="schedule in schedules"
                         :key="schedule.id"
                         :schedule="schedule"
                         :schedules="schedules" :lessons="lessons" :teachers="teachers" :classrooms="classrooms" :typeLessons="typeLessons">
            </schedule-tr>

            <tr>
                <td></td>
                <td>
                    <select v-model="newSchedule.lessonNumber">
                        <option value="" v-for="number in lessonNumber" v-bind:value="number">{{number}}</option>
                    </select>
                </td>
                <td>
                    <select v-model="newSchedule.lessonName">
                        <option value="" v-for="lesson in lessons" v-bind:value="lesson.name">{{lesson.name}}
                        </option>
                    </select>
                </td>
                <td>
                    <select v-model="newSchedule.lessonType">
                        <option value="" v-for="typeLesson in typeLessons" v-bind:value="typeLesson.type">
                            {{typeLesson.type}}
                        </option>
                    </select>
                </td>
                <td>
                    <input type="text" v-model="newSchedule.lessonTopic" @keyup.enter="add">
                </td>
                <td>
                    <select v-model="newSchedule.teacherSurname">
                        <option value="" v-for="teacher in teachers" v-bind:value="teacher.surname">
                            {{teacher.surname}}
                        </option>
                    </select>
                </td>
                <td>
                    <select v-model="newSchedule.classroomBuilding">
                        <option value="" v-for="classroom in classrooms" v-bind:value="classroom.building">
                            {{classroom.building}}
                        </option>
                    </select>
                </td>
                <td>
                    <select v-model="newSchedule.classroomCabinet">
                        <option value="" v-for="classroom in classrooms" v-bind:value="classroom.cabinet">
                            {{classroom.cabinet}}
                        </option>
                    </select>
                </td>
                <td>
                    <input type="date" v-model="newSchedule.lessonDate">
                </td>
                <td>
                    <input type="button" value="+" @click="add">
                </td>
                <td>
                    <input type="button" value="x" @click="clear">
                </td>
            </tr>
        </table>
    </div>
</template>
<script>
    import ScheduleTr from "./components/table/ScheduleTr.vue";

    export default {
        data() {
            return {
                scheduleApi: this.$resource("/schedule{/id}"),
                lessonApi: this.$resource("/lesson{/id}"),
                typeLessonApi: this.$resource("/type-lesson{/id}"),
                teacherApi: this.$resource("/teacher{/id}"),
                classroomApi: this.$resource("/classroom{/id}"),

                lessonNumber: [1, 2, 3, 4, 5],

                schedules: [],
                lessons: [],
                typeLessons: [],
                teachers: [],
                classrooms: [],

                newSchedule: {
                    lessonNumber: 1,
                    lessonName: null,
                    lessonType: null,
                    lessonTopic: null,
                    teacherSurname: null,
                    classroomBuilding: null,
                    classroomCabinet: null,
                    lessonDate: null
                }
            }
        },
        created() {
            this.getScheduleFromDb();
            this.getLessonFromDb();
            this.getTypeLessonFromDb();
            this.getTeacherFromDb();
            this.getClassroomFromDb();
        },
        components: {
            ScheduleTr
        },
        methods: {
            getScheduleFromDb() {
                this.scheduleApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(schedule => this.schedules.push(schedule))
                    })
                })
            },
            getLessonFromDb() {
                this.lessonApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(lesson => this.lessons.push(lesson))
                    })
                })
            },
            getTypeLessonFromDb() {
                this.typeLessonApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(typeLesson => this.typeLessons.push(typeLesson))
                    })
                })
            },
            getTeacherFromDb() {
                this.teacherApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(teacher => this.teachers.push(teacher))
                    })
                })
            },
            getClassroomFromDb() {
                this.classroomApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(classroom => this.classrooms.push(classroom))
                    })
                })
            },
            add() {
                this.newSchedule.lessonDate = this.convertDateInMillis(this.newSchedule.lessonDate);

                this.scheduleApi.save(this.newSchedule).then(response => {
                    response.json().then(data => {
                        this.schedules.push(data)
                    })
                });
                this.clear();
            },
            clear() {
                this.newSchedule.lessonNumber = 1;
                this.newSchedule.lessonName = null;
                this.newSchedule.lessonType = null;
                this.newSchedule.lessonTopic = null;
                this.newSchedule.teacherSurname = null;
                this.newSchedule.classroomBuilding = null;
                this.newSchedule.classroomCabinet = null;
                this.newSchedule.lessonDate = null;
            },
            convertDateInMillis(value) {
                return new Date(value).getTime();
            }
        }
    }
</script>
<style>
    table {
        margin-top: 4em;
        margin-left: auto;
        margin-right: auto;
    }
</style>