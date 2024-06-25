<template>
  <b-overlay :show="busy" variant="light">
    <Bar :chart-id="id" :chart-data="chartData" :chart-options="chartOptions" class="chart bar-chart">
      <slot>Error rendering chart!</slot>
    </Bar>
  </b-overlay>
</template>

<script>
import formatterMixin from "@/mixins/formatterMixin";
import chroma from "chroma-js";
import { Bar } from "vue-chartjs/legacy";
import { BarElement, CategoryScale, Chart, Legend, LinearScale, Title, Tooltip } from "chart.js";

Chart.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);
const scale = 