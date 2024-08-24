<template>
  <div id="statistics">
    <h1 class="d-none">Statistics</h1>
    <b-container>
      <b-row>
        <b-col>
          <b-skeleton-wrapper :loading="loading">
            <template #loading>
              <div class="mb-4">
                <b-skeleton width="100%" />
                <b-skeleton width="100%" />
                <b-skeleton width="100%" class="d-lg-none" />
                <b-skeleton width="100%" class="d-md-none d-lg-none" />
                <b-skeleton width="100%" class="d-md-none d-lg-none" />
                <b-skeleton width="100%" class="d-md-none d-lg-none" />

                <b-skeleton width="85%" class="d-lg-none d-md-block d-none" />
                <b-skeleton width="90%" class="d-xl-none d-lg-block d-none" />
                <b-skeleton width="35%" class="d-xl-block d-md-none" />
              </div>
            </template>
            <template #default>
              <p class="text-justify">
                The platform is host to
                <b-abbr :title="count.funcs.toLocaleString()">{{ formatNatural(count.funcs) }}</b-abbr>
                functions, sourced from
                <b-abbr :title="count.files.toLocaleString()">{{ formatNatural(count.files) }}</b-abbr>
                files, originating from
                <b-abbr :title="count.repos.toLocaleString()">{{ formatNatural(count.repos) }}</b-abbr>
                repositories. In total, we have mined {{ formatBytes(code.size) }} of source code, and analysed
                <b-abbr :title="code.lines.toLocaleString()">{{ formatNatural(code.lines) }}</b-abbr> lines. The
                platform currently has {{ count.users ? count.users : "no" }} registered users, and since its inception
                {{ count.tasks }} {{ count.tasks === 1 ? "dataset has" : "datasets have" }} been constructed. This
                amounts to roughly {{ formatBytes(tasks) }} in compressed file size.
              </p>
            </template>
          </b-skeleton-wrapper>
        </b-col>
      </b-row>
    </b-container>
    <b-container>
      <b-row align-h="center">
        <b-col cols="12" md="8" lg="6" xl="4">
          <b-bar-chart title="Number of repositories by language" :supplier="reposByLanguage" class="mb-3 mb-xl-0" />
        </b-col>
        <b-col cols="12" md="8" lg="6" xl="4">
          <b-bar-chart title="Number of files by language" :supplier="filesByLanguage" class="mb-3 mb-xl-0" />
        </b-col>
        <b-col cols="12" md="8" lg="6" xl="4">
          <b-bar-chart title="Number of functions by language" :supplier="funcsByLanguage" class="mb-3 mb-xl-0" />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import formatterMixin from "@/mixins/formatterMixin";
import BAbbr from "@/components/Abbr";
import BBarChart from "@/components/BarChart";

export default {
  components: {
    BAbbr,
    BBarChart,
  },
  mixins: [formatterMixin],
  methods: {
    async apiCall(endpoint) {
      return this.$http.get(endpoint).then((res) => res.data);
    },
    async totalUsers() {
      return this.apiCall("/statistics/users");
    },
    async totalRepos() {
      return this.apiCall("/statistics/repos");
    },
    async totalFiles() {
      return this.apiCall("/statistics/files");
    },
    async totalFuncs() {
      return this.apiCall("/statistics/functions");
    },
    async totalTasks() {
      return this.apiCall("/statistics/tasks");
    },
    async totalCodeSize() {
      return this.apiCall("/statistics/code/size");
    },
    async totalCodeLines() {
      return th