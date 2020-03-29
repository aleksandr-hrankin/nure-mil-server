<template>
    <tr v-if="editingMode">
        <td>{{teacher.id}}</td>
        <td>{{teacher.surname}}</td>
        <td>{{teacher.name}}</td>
        <td>{{teacher.patronymic}}</td>
        <td>{{teacher.degree}}</td>
        <td>{{teacher.dob}}</td>
        <td>{{teacher.phoneNumber}}</td>
        <td>{{teacher.photo}}</td>
        <td>
            <input type="button" value="E" @click="enableEditMode">
        </td>
        <td>
            <input type="button" value="X" @click="del">
        </td>
    </tr>
    <tr v-else>
        <td>{{teacher.id}}</td>
        <td><input type="text" v-model="newTeacher.surname" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.name" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.patronymic" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.degree" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.dob" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.phoneNumber" @keyup.enter="update"></td>
        <td><input type="text" v-model="newTeacher.photo" @keyup.enter="update"></td>
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
        props: ["teacher", "teachers"],
        data() {
            return {
                teacherApi: this.$resource("/teacher{/id}"),

                editingMode: true,

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
        methods: {
            enableEditMode() {
                this.newTeacher = this.teacher;
                this.editingMode = false;
            },
            offEditMode() {
                this.editingMode = true
            },
            update() {
                this.teacher = this.newTeacher;

                this.teacherApi.update({id: this.teacher.id}, this.teacher).then(response => {
                    response.json().then(data => {
                        let index = getIndexOfList(this.teacher, this.teacher.id);
                        this.teachers.splice(index, 1, data)
                    })
                });

                this.offEditMode();
            },
            del() {
                this.teacherApi.delete({id: this.teacher.id}).then(response => {
                    if (response.ok) {
                        this.teachers.splice(this.teachers.indexOf(this.teacher), 1)
                    }
                })
            },
        }
    }
</script>
<style>

</style>