<template>
    <div>
        <table>
            <tr>
                <th>№</th>
                <th>Корпус</th>
                <th>Кабінет</th>
                <th>Назва</th>
                <th></th>
                <th></th>
            </tr>

            <classroom-tr v-for="classroom in classrooms"
                       :key="classroom.id"
                       :classroom="classroom"
                       :classrooms="classrooms">
            </classroom-tr>

            <tr>
                <td></td>
                <td><input type="text" v-model="newClassroom.building" @keyup.enter="add"></td>
                <td><input type="text" v-model="newClassroom.cabinet" @keyup.enter="add"></td>
                <td><input type="text" v-model="newClassroom.name" @keyup.enter="add"></td>
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

    import ClassroomTr from "./components/table/ClassroomTr.vue";

    export default {
        data() {
            return {
                classroomApi: this.$resource("/classroom{/id}"),

                classrooms: [],

                newClassroom: {
                    name: null
                }
            }
        },
        created() {
            this.getClassroomFromDb()
        },
        components: {
            ClassroomTr
        },
        methods: {
            getClassroomFromDb() {
                this.classroomApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(classroom => this.classrooms.push(classroom))
                    })
                })
            },
            add() {
                this.classroomApi.save(this.newClassroom).then(response => {
                    response.json().then(data => {
                        this.classrooms.push(data)
                    })
                });
                this.clear();
            },
            clear() {
                this.newClassroom.building = null;
                this.newClassroom.cabinet = null;
                this.newClassroom.name = null;
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