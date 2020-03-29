import Vue from "vue"
import VueResource from "vue-resource"
import router from "./router/router";

import Home from "./pages/Home.vue";

Vue.use(VueResource);

new Vue({
    el: "#app",
    router,
    render: a => a(Home)
});