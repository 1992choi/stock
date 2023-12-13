<template>
  <div class="container-sm w-50 mt-5">
    <div class="authentication-wrapper authentication-basic container-p-y">
      <div class="authentication-inner">
        <!-- Register -->
        <div class="card">
          <div class="card-body">
            <div class="app-brand justify-content-center">
              <a class="app-brand-link gap-2">
                <span class="demo text-body fw-bolder">Stock</span>
              </a>
            </div>
            <form class="mb-3">
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control" v-model="userEmail" />
              </div>
              <div class="mb-3 form-password-toggle">
                <div class="d-flex justify-content-between">
                  <label class="form-label" for="password">Password</label>
                </div>
                <div class="input-group input-group-merge">
                  <input type="password" class="form-control" v-model="userPassword" />
                  <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
                </div>
              </div>
              <div class="mb-3">
                <button class="btn btn-primary d-grid w-100" type="button" @click="login()">Sign in</button>
              </div>
            </form>
            <p class="text-center">
              <a href=""><span>Sign up</span></a>
            </p>
          </div>
        </div>
        <!-- /Register -->
      </div>
    </div>
  </div>
</template>

<script setup>
  import {ref} from "@vue/reactivity";
  import axios from "axios";

  definePageMeta({
    layout: false
  });

  const userEmail = ref('')
  const userPassword = ref('')

  async function login() {
  await axios.post('http://localhost:8080/api/login', {
    userEmail: userEmail.value,
    userPassword: userPassword.value
  }).then((response) => {
    if (response.data.status == 'success') {
      alert("로그인 성공");
      location.href = 'main'
    } else {
      alert(response.data.message);
    }
  }).catch((error) => {
    console.log(error.response)
    alert("로그인 실패");
  });
}
</script>