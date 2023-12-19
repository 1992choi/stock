<template>
  <div class="container-sm w-50 mt-5">
    <div class="authentication-wrapper authentication-basic container-p-y">
      <div class="authentication-inner">
        <div class="card">
          <div class="card-body">
            <div class="app-brand justify-content-center">
              <a class="app-brand-link gap-2">
                <span class="demo text-body fw-bolder">Stock</span>
              </a>
            </div>
            <form class="mb-3">
              <div class="mb-3">
                <label for="userEmail" class="form-label">Email</label>
                <input type="text" class="form-control" v-model="userEmail" />
              </div>
              <div class="mb-3">
                <label for="userName" class="form-label">Name</label>
                <input type="text" class="form-control" v-model="userName" />
              </div>
              <div class="mb-3 form-password-toggle">
                <label class="form-label" for="userPassword">Password</label>
                <input type="password" class="form-control" v-model="userPassword" />
              </div>
              <div class="mb-3">
                <button class="btn btn-primary d-grid w-100" type="button" @click="signUp()">Sign up</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from "@vue/reactivity";
import axios from "axios";

definePageMeta({
  layout: false
});

const userEmail = ref('')
const userName = ref('')
const userPassword = ref('')
const router = useRouter();

async function signUp() {
  await axios.post('http://localhost:8080/api/users', {
    userEmail: userEmail.value,
    userName: userName.value,
    userPassword: userPassword.value
  }).then((response) => {
    if (response.data.status == 'success') {
      alert('회원가입 성공');
      router.push('/login');
    } else {
      alert(response.data.message);
    }
  }).catch((error) => {
    console.log(error.response)
    alert('회원가입 실패');
  });
}
</script>