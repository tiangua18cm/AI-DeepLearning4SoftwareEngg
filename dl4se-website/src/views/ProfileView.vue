<template>
  <div id="profile" v-if="fetched">
    <h1 class="d-none">Profile</h1>
    <b-container>
      <b-row>
        <b-col class="mb-3 d-flex justify-content-center justify-content-md-start align-items-center">
          <b-icon-identicon width="4em" height="4em" :identifier="user.uid" class="mr-2" />
          <div>
            <h2 class="m-0 user-select-none">{{ user.uid }}</h2>
            <span>Joined: {{ new Date(user.registered).toISOString().slice(0, 10) }}</span>
          </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-card class="rounded-0" no-body>
            <b-tabs active-nav-item-class="border-md-bottom" align="center" :vertical="$screen.md" card>
              <b-tab title="General" active>
                <b-card-title title-tag="h2" class="d-none">Update information</b-card-title>
                <b-card-sub-title sub-title-tag="h3" sub-title-text-variant="">Username</b-card-sub-title>
                <b-form @submit.stop.prevent="updateUid">
                  <b-form-row>
                    <b-form-group class="col">
                      <b-input
                        type="text"
                        placeholder="New username"
                        v-model.trim="form.uid"
                        :state="!v$.form.uid.$invalid"
                        :spellcheck="false"
                      />
                      <b-form-invalid-feedback v-if="v$.form.uid.$invalid">
                        <b-icon-exclamation-triangle />
                        <span>
                          Must be between 3 and 64 characters long, consisting only of alphanumeric characters, dashes
                          and underscores.
                        </span>
                      </b-form-invalid-feedback>
                    </b-form-group>
                  </b-form-row>
                  <b-form-row>
                    <b-form-group class="col">
                      <div
                        :class="{
                          'row-gap-3': true,
                          'column-gap-2': true,
                          'd-grid': !$screen.sm,
                          'd-inline-flex': $screen.sm,
                        }"
                      >
                        <b-form-submit :disabled="!canUpdateUid">Save</b-form-submit>
                        <b-button type="button" @click="form.uid = generateUsername()">Random</b-button>
                      </div>
      