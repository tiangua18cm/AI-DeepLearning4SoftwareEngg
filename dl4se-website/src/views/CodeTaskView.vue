
<template>
  <div id="task" v-if="show">
    <h1 class="d-none">Create Code Dataset</h1>
    <b-form @submit.prevent.stop="submit" class="container bg-light border">
      <b-form-row>
        <b-col>
          <h2>Repository Sample Characteristics</h2>
        </b-col>
      </b-form-row>
      <b-form-row>
        <b-form-group :state="!v$.task.query.language_name.$invalid" class="col-12">
          <b-dropdown-select
            id="language-select"
            placeholder="Language"
            :options="options.languages"
            v-model="task.query.language_name"
          >
            <template #header>Choose a language</template>
          </b-dropdown-select>
          <template #invalid-feedback>Language is required</template>
        </b-form-group>
        <b-form-group label="Commits:" class="col-12 col-sm-6 col-md-3">
          <b-counter
            id="commits-input"
            placeholder="min"
            v-model.number="task.query.min_commits"
            :state="validate(v$.task.query.min_commits)"
            :min="0"
          />
        </b-form-group>
        <b-form-group label="Issues:" class="col-12 col-sm-6 col-md-3">
          <b-counter
            id="issues-input"
            placeholder="min"
            v-model.number="task.query.min_issues"
            :state="validate(v$.task.query.min_issues)"
            :min="0"
          />
        </b-form-group>
        <b-form-group label="Contributors:" class="col-12 col-sm-6 col-md-3">
          <b-counter
            id="contributors-input"
            placeholder="min"
            v-model.number="task.query.min_contributors"
            :state="validate(v$.task.query.min_contributors)"
            :min="0"
          />
        </b-form-group>
        <b-form-group label="Stars:" class="col-12 col-sm-6 col-md-3">
          <b-counter
            id="stars-input"
            placeholder="min"
            v-model.number="task.query.min_stars"
            :state="validate(v$.task.query.min_stars)"
            :min="0"
          />
        </b-form-group>
        <b-form-group class="col-12">
          <b-checkbox id="license-checkbox" v-model="task.query.has_license" :inline="$screen.sm">
            Has Open-source License
          </b-checkbox>
          <b-checkbox id="forks-checkbox" v-model="task.query.exclude_forks" :inline="$screen.sm">
            Exclude Forks
          </b-checkbox>
        </b-form-group>
      </b-form-row>
      <b-form-row>
        <b-col>
          <hr />
          <h2>Dataset Characteristics</h2>
        </b-col>
      </b-form-row>
      <b-form-row class="column-gap-3">
        <b-form-group class="col-12 col-md-auto">
          <template #label>
            Granularity
            <b-documentation-link page="documentation" section="#granularity" tabindex="-1" target="_blank" />
          </template>
          <b-form-radio-group
            id="type-radio"
            v-model="task.query.granularity"
            :options="options.granularities"
            :stacked="$screen.md"
            required
          />
        </b-form-group>
        <b-form-group class="col-12 col-md">
          <template #label>
            Metadata
            <b-documentation-link page="documentation" section="#meta" tabindex="-1" target="_blank" />
          </template>
          <b-form-checkbox id="sex-checkbox" v-model="task.processing.include_symbolic_expression">
            Pair each instance with its Symbolic Expression representation
          </b-form-checkbox>
          <b-form-text v-show="task.processing.include_symbolic_expression">
            Choosing to include S-Expressions in your dataset will increase the size of the exported file.
          </b-form-text>
          <b-form-checkbox id="ast-checkbox" v-model="task.processing.include_ast">
            Pair each instance with its AST-based representation
          </b-form-checkbox>
          <b-form-text v-show="task.processing.include_ast">
            Choosing to include ASTs in your dataset will <strong>drastically</strong> increase the size of the exported
            file.
          </b-form-text>
          <b-form-checkbox id="ts-checkbox" v-model="task.processing.include_tree_sitter_version">
            Pair each instance with <code>tree-sitter</code> parser metadata
          </b-form-checkbox>
          <b-form-text v-show="task.processing.include_tree_sitter_version">
            Enabling this will include the version of the <code>tree-sitter</code> parser which was used to compute all
            the instance information. This meta-information is used primarily for troubleshooting, and as such is
            unlikely to benefit the average user. For this reason we recommend keeping it turned off.
          </b-form-text>
        </b-form-group>
      </b-form-row>
      <b-form-row>
        <b-col>
          <hr />
          <h2>Code Filters &amp; Processing</h2>
        </b-col>
      </b-form-row>
      <b-row>
        <b-col cols="12" md="6">
          <b-form-row>
            <b-form-group label="Characters:" class="col-12 col-sm-6">
              <b-counter
                id="characters-input-min"
                placeholder="min"
                v-model.number="task.query.min_characters"
                :min="0"
                :max="coalesceMax(task.query.max_characters)"
                :state="validate(v$.task.query.min_characters)"
              />
            </b-form-group>
            <b-form-group class="col-12 col-sm-6 align-self-end">
              <b-counter
                id="characters-input-max"
                placeholder="max"
                v-model.number="task.query.max_characters"
                :min="coalesceMin(task.query.min_characters)"
                :state="validate(v$.task.query.max_characters)"
              />
            </b-form-group>
            <b-form-group label="Tokens:" class="col-12 col-sm-6">
              <b-counter
                id="tokens-input-min"
                placeholder="min"
                v-model.number="task.query.min_tokens"
                :min="0"
                :max="coalesceMax(task.query.max_tokens)"
                :state="validate(v$.task.query.min_tokens)"
              />
            </b-form-group>
            <b-form-group class="col-12 col-sm-6 align-self-end">
              <b-counter
                id="tokens-input-max"
                placeholder="max"
                v-model.number="task.query.max_tokens"
                :min="coalesceMin(task.query.min_tokens)"
                :state="validate(v$.task.query.max_tokens)"
              />
            </b-form-group>