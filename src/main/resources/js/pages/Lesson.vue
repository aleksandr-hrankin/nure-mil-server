<template>
    <div>
        <table>
            <tr>
                <th>№</th>
                <th>Назва</th>
                <th></th>
                <th></th>
            </tr>

            <lesson-tr v-for="lesson in lessons"
                         :key="lesson.id"
                         :lesson="lesson"
                         :lessons="lessons">
            </lesson-tr>

            <tr>
                <td></td>
                <td>
                    <input type="text" v-model="newLesson.name">
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

    import LessonTr from "./components/table/LessonTr.vue";

    export default {
        data() {
            return {
                lessonApi: this.$resource("/lesson{/id}"),

                lessons: [],

                newLesson: {
                    name: null
                }
            }
        },
        created() {
            this.getLessonFromDb()
        },
        components: {
            LessonTr
        },
        methods: {
            getLessonFromDb() {
                this.lessonApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(lesson => this.lessons.push(lesson))
                    })
                })
            },
            add() {
                this.lessonApi.save(this.newLesson).then(response => {
                    response.json().then(data => {
                        this.lessons.push(data)
                    })
                });
                this.clear();
            },
            clear() {
                this.newLesson.name = null;
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