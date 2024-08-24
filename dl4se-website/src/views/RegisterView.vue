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
            <template #label>
              Password
              <b-icon-asterisk font-scale="0.35" shift-v="16" class="text-danger" />
            </template>
            <b-form-input-password
              id="password"
              name="password"
              autocomplete="new-password"
              v-model.trim="form.password"
              :disabled="submitted"
              :state="v$.form.password.$dirty ? !v$.form.password.$invalid : null"
            />
            <b-form-text v-if="v$.form.password.$invalid">
              <b-icon-exclamation-octagon />
              <span>Must be at least 6 characters long, containing a number, uppercase and lowercase character.</span>
            </b-form-text>
          </b-form-group>
        </b-form-row>
        <b-form-row>
          <b-form-group label-for="confirm">
            <template #label>
              Confirm Password
              <b-icon-asterisk font-scale="0.35" shift-v="16" class="text-danger" />
            </template>
            <b-form-input-password
              id="confirm"
              name="confirm"
              autocomplete="new-password"
              v-model.trim="form.confirm"
              :disabled="submitted"
              :state="v$.form.confirm.$dirty ? !v$.form.confirm.$invalid : null"
            />
            <b-form-invalid-feedback v-if="v$.form.confirm.$dirty && v$.form.confirm.$invalid">
              <b-icon-exclamation-triangle />
              Must match the password above.
            </b-form-invalid-feedback>
          </b-form-group>
        </b-form-row>
        <b-form-row>
          <!-- FIXME: We have to do this manually because the inner group div can not be customized -->
          <div role="group" class="form-group">
            <label for="organisation" class="d-block">
              Organisation
              <b-icon-asterisk font-scale="0.35" shift-v="16" class="text-danger" />
            </label>
            <div class="position-relative">
              <b-form-auto-complete
                id="organisation"
                name="organisation"
                type="text"
                v-model.trim="form.organisation"
                :server="organisationsURL"
                query-param="name"
                :debounce-time="250"
                :server-params="{ size: 5 }"
                :response-mapper="responseMapper"
                :disabled="submitted"
                :state="v$.form.organisation.$dirty ? !v$.form.organisation.$invalid : null"
              />
            </div>
            <b-form-text v-if="v$.form.organisation.$invalid">
              <b-icon-exclamation-octagon />
              We use this information for analytics.
            </b-form-text>
          </div>
        </b-form-row>
        <b-form-row>
          <b-form-group>
            <b-form-text text-variant="dark">
              <b-icon-asterisk font-scale="0.35" class="text-danger" />
              Required fields
            </b-form-text>
          </b-form-group>
        </b-form-row>
        <b-form-row>
          <b-form-group>
            <b-form-submit :disabled="v$.$invalid || submitted" />
          </b-form-group>
        </b-form-row>
        <b-overlay :show="submitted" variant="light" no-wrap :z-index="Number.MAX_SAFE_INTEGER" />
      </b-form>
    </b-container>
  </div>
</template>

<script>
import useVuelidate from "@vuelidate/core";
import { email, required, sameAs } from "@vuelidate/validators";
import { pass