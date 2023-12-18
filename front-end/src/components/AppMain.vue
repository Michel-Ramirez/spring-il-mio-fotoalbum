<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';

const term = ref("");
const pictures = ref(null);
const fetchPictures = async () => {

    if (term.value !== "") {
        const data = await axios.get(`http://127.0.0.1:8080/api/v1.0/pictures?query=${term.value}`);
        pictures.value = data.data;
    } else {
        const data = await axios.get('http://127.0.0.1:8080/api/v1.0/pictures');
        pictures.value = data.data;
    }
}

onMounted(fetchPictures)
</script>

<template>
    <main class="container">

        <h1 class="my-5 text-center ">Welcome to Spring of Picture</h1>

        <div class="searchbar">
            <form class="d-flex" role="search" @submit.prevent="fetchPictures(term)">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" v-model.trim="term">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>

        <div class="d-flex flex-wrap ">
            <div v-for="pic in pictures" @click="$emit('openPicture', pic)" class="card m-3" style="width: 25rem;">
                <img :src="pic.img" class="card-img-top img-fluid mt-3" alt="...">
                <div class="card-body d-flex flex-column justify-content-between">
                    <div class="mb-5">
                        <h5 class="card-title">{{ pic.title }}</h5>
                        <p class="card-text">{{ pic.description }}</p>
                    </div>
                    <p class="text-end">
                        <span class="fw-bolder me-2">Photographer :</span><i> {{ pic.user.name }} {{ pic.user.surname }}</i>
                    </p>
                </div>
            </div>
        </div>
    </main>
</template>

<style scoped>
img {
    height: 200px;
    object-fit: contain;
}

.card {

    cursor: pointer;
}

.searchbar {
    display: flex;
    justify-content: center;
    margin-bottom: 50px;
}

form {
    width: 600px;
}
</style>