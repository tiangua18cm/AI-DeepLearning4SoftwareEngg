<template>
  <div :id="`code-${id}`" v-show="show">
    <b-container>
      <b-row>
        <b-col>
          <h1 v-if="!code" class="text-center">Instance could not be found!</h1>
          <b-card v-else class="rounded-0" no-body>
            <b-tabs :align="tabsAlign" card>
              <b-tab title="Description" active>
                <b-table-simple :sticky-header="tableHeight" class="m-0" responsive>
                  <b-tr>
                    <b-td>Repository:</b-td>
                    <b-td class="text-monospace">
                      <b-link :href="repoURL" target="_blank">
                        {{ code.repo.name }}
                      </b-link>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Last Commit SHA:</b-td>
                    <b-td class="text-monospace">
                      <b-link :href="repoTreeURL" target="_blank">
                        <template v-if="!$screen.md">
                          {{ code.repo.last_commit_sha.substr(0, 7) }}
                        </template>
                        <template v-else>
                          {{ code.repo.last_commit_sha }}
                        </template>
                      </b-link>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Last Commit Date:</b-td>
                    <b-td>{{ code.repo.last_commit + "Z" }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Path:</b-td>
                    <b-td class="text-monospace">
                      <b-link :href="repoFileURL" target="_blank">
                        {{ filePath }}
                      </b-link>
                    </b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Language:</b-td>
                    <b-td>{{ code.language_name }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Characters:</b-td>
                    <b-td>{{ code.characters }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Code Tokens:</b-td>
                    <b-td>{{ code.code_tokens }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Total Tokens:</b-td>
                    <b-td>{{ code.total_tokens }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td>Lines:</b-td>
                    <b-td>{{ code.lines }}</b-td>
                  </b-tr>
                  <b-tr>
                    <b-td :colspan="!hasLicense ? 2 : null">License:</b-td>
                    <b-td v-if="hasLicense">{{ code.repo.license }}</b-td>
                  </b-t