<template>
  <div id="register">
    <h1 class="text-center">Register</h1>
    <b-container>
      <b-form @submit.prevent.stop="register" novalidate>
        <b-form-row>
          <b-form-group label-for="email">
            <template #label>
              Email
              <b-icon-asterisk font-scale="0.35" shift-v="16" class="text-danger" />
            </template>
            <b-form-input
              id="email"
              name="email"
              type="email"
              autocomplete="email"
              v-model.trim="form.email"
              :disabled="submitted"
              :state="v$.form.email.$dirty ? !v$.form.email.$invalid : null"
              placeholder="example@email.com"
              autofocus
              @blur="autofillOrganisation"
            />
          </b-form-group>
        </b-form-row>
        <b-form-row>
          <b-form-group label-for="password">
            