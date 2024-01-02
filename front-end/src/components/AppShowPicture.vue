<script setup>
import { ref, defineProps } from 'vue'
import axios from 'axios'

const props = defineProps({
    picture: Object
})

const alertMsgSended = ref(false);
const errorName = ref(false)
const errorEmail = ref(false)
const errorMessage = ref(false)

const message = ref({
    name: "",
    email: "",
    message: ""
})


const sendMessage = async () => {

    if (message.value.name === "") {
        errorName.value = true
    } else if (message.value.email === "") {
        errorEmail.value = true
        
    } else if(message.value.message === "") {

        errorMessage.value = true
    } else {
        const data = await axios.post(`http://localhost:8080/api/v1.0/messages?userId=${picture.user.id}`, message.value);
        if (data.status === 200) {
            alertMsgSended.value = true
        }    
    }


}
const picture = props.picture
</script>

<template>
    <main class="container mb-5">

        <div class="mt-5 d-flex justify-content-end ">
            <div @click="$emit('closePic', picture == null)" class="btn btn-secondary">Return to Home</div>
        </div>

        <h1 class="mt-5">{{ picture.title }}</h1>

<<<<<<< HEAD
        <div>
            <div v-for="cat in picture.categories" class="badge text-bg-primary me-3 mb-5">{{ cat.name }}</div>
        </div>

        <figure class="d-flex justify-content-center ">
=======
        <span v-if="picture.categories" v-for="cat in picture.categories" class="text-end">
            <span class="badge text-bg-primary m-1">{{cat.name}}</span>
        </span>

        <figure class="d-flex justify-content-center mt-5">
>>>>>>> 8565c3ece541b3542ffdc66fc2b7df663092be85
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
            <form @submit.prevent="sendMessage" method="POST">
                <h4 class="mb-3">Contact</h4>

                <div v-if="alertMsgSended" class="alert alert-success">
                    <p class="m-0">Message sent successfully</p>
                </div>

                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" v-model.trim="message.name">
                            <div v-if="errorName" class="alert alert-danger">
                                <p class="m-0">this field is required</p>
                            </div>
                        </div>
                        <div class="col-6">
                            <label for="email" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="email" name="email" v-model.trim="message.email">
                            <div v-if="errorEmail" class="alert alert-danger">
                                <p class="m-0">this field is required</p>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" rows="3" name="text"
                            v-model.trim="message.message"></textarea>
                            <div v-if="errorMessage" class="alert alert-danger">
                                <p class="m-0">this field is required</p>
                            </div>
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