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
                <span class="align-middle">Filter Setti