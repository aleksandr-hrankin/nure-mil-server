<template>
    <tr v-if="editingMode">
        <td>{{typeLesson.id}}</td>
        <td>{{typeLesson.type}}</td>
        <td>
            <input type="button" value="E" @click="enableEditMode">
        </td>
        <td>
            <input type="button" value="X" @click="del">
        </td>
    </tr>
    <tr v-else>
        <td>{{typeLesson.id}}</td>
        <td>
            <input type="text" v-model="newTypeLesson.type" @keyup.enter="update">
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
        props: ["typeLesson", "typeLessons"],
        data() {
            return {
                typeLessonApi: this.$resource("/type-lesson{/id}"),

                editingMode: true,

                newTypeLesson: {
                    type: null,
                }
            }
        },
        methods: {
            enableEditMode() {
                this.newTypeLesson = this.typeLesson;
                this.editingMode = false;
            },
            offEditMode() {
                this.editingMode = true
            },
            update() {
                this.typeLesson = this.newTypeLesson;

                this.typeLessonApi.update({id: this.typeLesson.id}, this.typeLesson).then(response => {
                    response.json().then(data => {
                        let index = getIndexOfList(this.typeLessons, this.typeLesson.id);
                        this.typeLessons.splice(index, 1, data)
                    })
                });

                this.offEditMode();
            },
            del() {
                this.typeLessonApi.delete({id: this.typeLesson.id}).then(response => {
                    if (response.ok) {
                        this.typeLessons.splice(this.typeLessons.indexOf(this.typeLesson), 1)
                    }
                })
            },
        }
    }
</script>
<style>

</style>