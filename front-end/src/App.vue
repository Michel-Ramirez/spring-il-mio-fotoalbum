<script setup>
import AppHeader from './components/AppHeader.vue';
import AppMain from './components/AppMain.vue';
import AppShowPicture from './components/AppShowPicture.vue';
import { ref } from 'vue'

const picture = ref(null)
const openPicture = (pic) => {
  picture.value = pic

}

const closePic = (closePic) => {
  picture.value = closePic
}
</script>

<template>
  <AppHeader />
  <AppMain @openPicture="openPicture" v-if="!picture" />
  <AppShowPicture :picture="picture" @closePic="closePic" v-else />
</template>

<style scoped></style>
