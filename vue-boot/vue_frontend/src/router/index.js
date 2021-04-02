import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home";
import About from "../views/About";
import colorMgmt from "../views/test/colorMgmt";
import scrollPage from "../views/test/scrollPage";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [{
      path: "/",
      component: Home
    },
    {
      path: "/about",
      component: About
    },
     {
      path: "/colorMgmt",
      component: colorMgmt
    },
    {
      path: "/scrollPage",
      component: scrollPage
    },
  ]
});

export default router;
