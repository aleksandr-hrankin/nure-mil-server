import Vue from "vue"
import VueResource from "vue-resource"

import Home from "./pages/Home.vue";

Vue.use(VueResource);

new Vue({
    el: "#app",
    render: a => a(Home)
});