<script setup>
import AppHeader from './components/AppHeader.vue';
import AppMain from './components/AppMain.vue';

</script>

<template>
  <AppHeader />
  <AppMain :pictures="pictures" />
</template>

<style scoped></style>
