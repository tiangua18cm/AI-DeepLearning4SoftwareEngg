import Vue from "vue";
import VueRouter from "vue-router";
import axios from "@/axios";

const HomeView = () => import("@/views/HomeView");
const LogInView = () => import("@/views/LogInView");
const DashboardView = () => import("@/views/DashboardView")