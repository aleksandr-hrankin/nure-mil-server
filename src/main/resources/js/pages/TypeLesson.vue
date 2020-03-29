<template>
    <div>
        <table>
            <tr>
                <th>№</th>
                <th>Тип</th>
                <th></th>
                <th></th>
            </tr>

            <type-lesson-tr v-for="typeLes in typeLessons"
                       :key="typeLes.id"
                       :typeLesson="typeLes"
                       :typeLessons="typeLessons">
            </type-lesson-tr>

            <tr>
                <td></td>
                <td>
                    <input type="text" v-model="newTypeLesson.type" @keyup.enter="add">
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

    import TypeLessonTr from "./components/table/TypeLessonTr.vue";

    export default {
        data() {
            return {
                typeLessonApi: this.$resource("/type-lesson{/id}"),

                typeLessons: [],

                newTypeLesson: {
                    type: null
                }
            }
        },
        created() {
            this.getTypeLessonFromDb()
        },
        components: {
            TypeLessonTr
        },
        methods: {
            getTypeLessonFromDb() {
                this.typeLessonApi.get().then(response => {
                    response.json().then(data => {
                        data.forEach(typeLesson => this.typeLessons.push(typeLesson))
                    })
                })
            },
            add() {
                this.typeLessonApi.save(this.newTypeLesson).then(response => {
                    response.json().then(data => {
                        this.typeLessons.push(data)
                    })
                });
                this.clear();
            },
            clear() {
                this.newTypeLesson.type = null;
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