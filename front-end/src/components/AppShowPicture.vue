<script setup>
import { ref, defineProps } from 'vue'

const props = defineProps({
    picture: Object
})

const picture = props.picture
</script>

<template>
    <main class="container mb-5">

        <h1 class="my-5">{{ picture.title }}</h1>

        <figure>
            <img :src="picture.img" :alt="picture.title" class="img-fluid">
        </figure>
        <p class="text-start">
            <span class="fw-bolder me-2">Photographer :</span><i> {{ picture.user.name }} {{ picture.user.surname }}</i>
        </p>
        <div>
            <p>{{ picture.description }}</p>
        </div>

        <h2 class="mt-5 pb-5">If you wish to contact the photographer you can do so here</h2>

        <div class="d-flex justify-content-center my-5">
            <form>
                <h4 class="mb-3">Contact</h4>
                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name">
                        </div>
                        <div class="col-6">
                            <label for="email" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="email">
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" rows="3"></textarea>
                    </div>
                </div>
                <div class="d-flex justify-content-center mt-5">
                    <button type="submit" class="btn btn-primary me-3">Send</button>
                    <button type="reset" class="btn btn-warning">Reset</button>
                </div>
            </form>
        </div>
    </main>
</template>

<style scoped>
form {
    width: 600px;
    padding: 50px;
    border: 1px solid gray;
    border-radius: 10px;
}
</style>