<template>
  <div id="admin">
    <h1 class="d-none">Admin</h1>
    <b-container class="d-flex flex-column row-gap-3">
      <b-row>
        <b-col>
          <h2>Platform Users</h2>
          <b-paginated-table
            :id="userTable.id"
            :fields="userTable.fields"
            :controls="['filters']"
            :primary-key="userTable.fields[0].key"
            :total-items="userTable.totalItems"
            :provider="userProvider"
            :sticky-header="tableHeight"
          >
            <template #controls(filters)>
              <b-button v-b-modal.user-filter-select block class="btn-controls">
                <b-icon-filter class="align-middle" font-scale="1.5" />
                <span class="align-middle">Filter Settings</span>
              </b-button>
            </template>
            <template #cell(uid)="row">
              <b-icon-identicon :identifier="row.item.uid" :scale="1.35" /> {{ row.value }}
            </template>
            <template #cell(organisation)="row">
              <span class="text-nowrap">{{ row.value }}</span>
            </template>
            <template #cell(registered)="row">
              <span v-b-tooltip="row.value.toISOString()">{{ row.value.toISOString().split("T")[0] }}</span>
            </template>
            <template #cell(details)="row">
              <div class="d-inline-flex gap-2">
                <b-icon
                  :icon="`patch-${row.item.verified ? 'check' : 'question'}-fill`"
                  v-b-tooltip="`Email ${row.item.verified ? 'Verified' : 'Unverified'}`"
                  scale="1.35"
                />
                <b-iconstack
                  v-b-tooltip="(row.item.enabled ? '' : 'Disabled ') + startCase(row.item.role)"
                  scale="1.35"
                >
                  <b-icon
                    :icon="row.item.role === 'ADMIN' ? 'person-plus-fill' : 'person-fill'"
                    :shift-h="row.item.role === 'ADMIN' ? 2 : 0"
                    stacked
                  />
                  <b-icon icon="x-lg" stacked variant="danger" v-if="!row.item.enabled" scale="0.8" />
                  <b-icon icon="circle" stacked variant="danger" v-if="!row.item.enabled" />
                </b-iconstack>
              </div>
            </template>
            <template #cell(actions)="row">
              <div class="d-inline-flex gap-1">
                <b-button
                  @click="userAction(row.item.uid, row.item.enabled ? 'disable' : 'enable')"
                  v-b-tooltip="row.item.enabled ? 'Disable' : 'Enable'"
                  size="sm"
                >
                  <b-icon :icon="`person-${row.item.enabled ? 'x' : 'check'}-fill`" />
                </b-button>
                <b-button
                  @click="userAction(row.item.uid, row.item.role === 'ADMIN' ? 'demote' : 'promote')"
                  v-b-tooltip="row.item.role === 'ADMIN' ? 'Demote' : 'Promote'"
                  size="sm"
                >
                  <b-icon :icon="`person-${row.item.role === 'ADMIN' ? 'dash' : 'plus'}-fill`" />
                </b-button>
              </div>
            </template>
          </b-paginated-table>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h2>Server Environment</h2>
          <div class="table-config-container">
            <b-overlay :show="configTable.busy" variant="light">
              <b-table-simple
                :id="configTable.id"
                :hover="hasConfigs"
                :sticky-header="tableHeight"
                class="table-container"
                responsive
                borderless
              >
                <b-thead head-variant="dark">
                  <b-tr>
                    <b-th>Property</b-th>
                    <b-th>Value</b-th>
                  </b-tr>
                </b-thead>
                <b-tbody class="bg-light">
                  <template v-if="hasConfigs">
                    <b-tr v-for="key in Object.keys(configTable.configs)" :key="key">
                      <b-td>
                        <label :for="key" class="text-monospace m-0">
                          {{ key }}
                        </label>
                      </b-td>
                      <b-td>
                        <b-input
                          :id="key"
                          :state="configState(key)"
                          :disable