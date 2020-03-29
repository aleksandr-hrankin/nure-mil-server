<template>
    <tr v-if="editingMode">
        <td>{{classroom.id}}</td>
        <td>{{classroom.building}}</td>
        <td>{{classroom.cabinet}}</td>
        <td>{{classroom.name}}</td>
        <td>
            <input type="button" value="E" @click="enableEditMode">
        </td>
        <td>
            <input type="button" value="X" @click="del">
        </td>
    </tr>
    <tr v-else>
        <td>{{classroom.id}}</td>
        <td><input type="text" v-model="newClassroom.building" @keyup.enter="update"></td>
        <td><input type="text" v-model="newClassroom.cabinet" @keyup.enter="update"></td>
        <td><input type="text" v-model="newClassroom.name" @keyup.enter="update"></td>
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
        props: ["classroom", "classrooms"],
        data() {
            return {
                classroomApi: this.$resource("/classroom{/id}"),

                editingMode: true,

                newClassroom: {
                    building: null,
                    cabinet: null,
                    name: null,
                }
            }
        },
        methods: {
            enableEditMode() {
                this.newClassroom = this.classroom;
                this.editingMode = false;
            },
            offEditMode() {
                this.editingMode = true
            },
            update() {
                this.classroom = this.newClassroom;

                this.classroomApi.update({id: this.classroom.id}, this.classroom).then(response => {
                    response.json().then(data => {
                        let index = getIndexOfList(this.classrooms, this.classroom.id);
                        this.classrooms.splice(index, 1, data)
                    })
                });

                this.offEditMode();
            },
            del() {
                this.classroomApi.delete({id: this.classroom.id}).then(response => {
                    if (response.ok) {
                        this.classrooms.splice(this.classrooms.indexOf(this.classroom), 1)
                    }
                })
            },
        }
    }
</script>
<style>

</style>