<template>
    <tr v-if="editingMode">
        <td>{{schedule.id}}</td>
        <td>{{schedule.lessonNumber}}</td>
        <td>{{schedule.lessonName}}</td>
        <td>{{schedule.lessonType}}</td>
        <td>{{schedule.lessonTopic}}</td>
        <td>{{schedule.teacherSurname}}</td>
        <td>{{schedule.classroomBuilding}}</td>
        <td>{{schedule.classroomCabinet}}</td>
        <td>{{convertDateInString(schedule.lessonDate)}}</td>
        <td>
            <input type="button" value="E" @click="enableEditMode">
        </td>
        <td>
            <input type="button" value="X" @click="del">
        </td>
    </tr>
    <tr v-else>
        <td>{{schedule.id}}</td>
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
            <input type="text" v-model="newSchedule.lessonTopic" @keyup.enter="update">
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
            <input type="button" value="S" @click="update">
        </td>
        <td>
            <input type="button" value="x" @click="offEditMode">
        </td>
    </tr>
</template>
<script>
    function getIndexOfList(list, id) {
        for (let i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }

    export default {
        props: ["schedule", "schedules", "lessons", "teachers", "classrooms", "typeLessons"],
        data() {
            return {
                scheduleApi: this.$resource("/schedule{/id}"),

                editingMode: true,

                lessonNumber: [1, 2, 3, 4, 5],

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
        methods: {
            enableEditMode() {
                this.newSchedule = this.schedule;

                this.editingMode = false;
            },
            offEditMode() {
                this.editingMode = true
            },
            update() {
                this.schedule = this.newSchedule;
                this.schedule.lessonDate = this.convertDateInMillis(this.schedule.lessonDate);

                this.scheduleApi.update({id: this.schedule.id}, this.schedule).then(response => {
                    response.json().then(data => {
                        let index = getIndexOfList(this.schedules, this.schedule.id);
                        this.schedules.splice(index, 1, data)
                    })
                });

                this.offEditMode();
            },
            del() {
                this.scheduleApi.delete({id: this.schedule.id}).then(response => {
                    if (response.ok) {
                        this.schedules.splice(this.schedules.indexOf(this.schedule), 1)
                    }
                })
            },
            convertDateInString(value) {
                let date = new Date(value);
                return date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear()
            },
            convertDateInMillis(value) {
                return new Date(value).getTime();
            }
        }
    }
</script>
<style>

</style>