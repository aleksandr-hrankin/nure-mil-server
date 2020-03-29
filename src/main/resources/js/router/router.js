import Vue from "vue"
import VueRouter from "vue-router";

import Schedule from "../pages/Schedule.vue";
import Lesson from "../pages/Lesson.vue";
import TypeLesson from "../pages/TypeLesson.vue";
import Teacher from "../pages/Teacher.vue";
import Classroom from "../pages/Classroom.vue";

Vue.use(VueRouter);

const routes = [
    { path: "/schedule-table", component: Schedule },
    { path: "/lesson-table", component: Lesson },
    { path: "/type-lesson-table", component: TypeLesson },
    { path: "/teacher-table", component: Teacher },
    { path: "/classroom-table", component: Classroom }
];

export default new VueRouter({
    mode: "history",
    routes
})