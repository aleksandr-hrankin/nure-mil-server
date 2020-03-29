import Vue from "vue"
import VueRouter from "vue-router";

import Schedule from "../pages/Schedule.vue";

Vue.use(VueRouter);

const routes = [
    { path: "/schedule", component: Schedule }
];

export default new VueRouter({
    routes
})