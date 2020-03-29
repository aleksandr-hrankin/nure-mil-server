<template>
    <tr v-if="editingMode">
        <td>{{lesson.id}}</td>
        <td>{{lesson.name}}</td>
        <td>
            <input type="button" value="E" @click="enableEditMode">
        </td>
        <td>
            <input type="button" value="X" @click="del">
        </td>
    </tr>
    <tr v-else>
        <td>{{lesson.id}}</td>
        <td>
            <input type="text" v-model="newLesson.name">
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
        props: ["lesson", "lessons"],
        data() {
            return {
                lessonApi: this.$resource("/lesson{/id}"),

                editingMode: true,

                newLesson: {
                    name: null,
                }
            }
        },
        methods: {
            enableEditMode() {
                this.newLesson = this.lesson;
                this.editingMode = false;
            },
            offEditMode() {
                this.editingMode = true
            },
            update() {
                this.lesson = this.newLesson;

                this.lessonApi.update({id: this.lesson.id}, this.lesson).then(response => {
                    response.json().then(data => {
                        let index = getIndexOfList(this.lessons, this.lesson.id);
                        this.lessons.splice(index, 1, data)
                    })
                });

                this.offEditMode();
            },
            del() {
                this.lessonApi.delete({id: this.lesson.id}).then(response => {
                    if (response.ok) {
                        this.lessons.splice(this.lessons.indexOf(this.lesson), 1)
                    }
                })
            },
        }
    }
</script>
<style>

</style>