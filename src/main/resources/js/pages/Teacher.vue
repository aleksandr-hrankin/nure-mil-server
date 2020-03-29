<template>
    <div>
        <table>
            <tr>
                <th>№</th>
                <th>Призвіще</th>
                <th>Ім'я</th>
                <th>Побатькові</th>
                <th>Звання</th>
                <th>Дата народження</th>
                <th>Телефон</th>
                <th>Фото</th>
                <th></th>
                <th></th>
            </tr>

            <teacher-tr v-for="teacher in teachers"
                       :key="teacher.id"
                       :teacher="teacher"
                       :teachers="teachers">
            </teacher-tr>

            <tr>
                <td></td>
                <td><input type="text" v-model="newTeacher.surname" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.name" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.patronymic" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.degree" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.dob" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.phoneNumber" @keyup.enter="add"></td>
                <td><input type="text" v-model="newTeacher.photo" @keyup.enter="add"></td>
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

    import TeacherTr from "./components/table/TeacherTr.vue";

    export default {
        data() {
            return {
                teacherApi: this.$resource("/teacher{/id}"),

                teachers: [],

                newTeacher: {
                    surname: null,
                    name: null,
                    patronymic: null,
                    degree: null,
                    dob: null,
                    phoneNumber: null,
                    photo: null,
                }
            }
        },
        created() {
            this.getTeacherFromDb()
        },
        components: {
            TeacherTr
        },
        methods: {
            getTeacherFromDb() {
                this.teacherApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(teacher => this.teachers.push(teacher))
                    })
                })
            },
            add() {
                this.teacherApi.save(this.newTeacher).then(response => {
                    response.json().then(data => {
                        this.teachers.push(data)
                    })
                });
                this.clear();
            },
            clear() {
                this.newTeacher.surname = null;
                this.newTeacher.name = null;
                this.newTeacher.patronymic = null;
                this.newTeacher.degree = null;
                this.newTeacher.dob = null;
                this.newTeacher.phoneNumber = null;
                this.newTeacher.photo = null;
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