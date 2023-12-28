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

<template>
  <div class="authentication">
    <v-container fluid class="pa-3">
      <v-row class="h-100vh d-flex justify-center align-center">
        <v-col cols="12" lg="4" xl="3" class="d-flex align-center">
          <v-card rounded="md" elevation="10" class="px-sm-1 px-0 withbg mx-auto" max-width="500">
            <v-card-item class="pa-sm-8">
              <div class="d-flex justify-center py-4">
                <LayoutFullLogo />
              </div>
              <v-row class="d-flex mb-3">
                <v-col cols="12">
                  <v-label class="font-weight-bold mb-1">Email</v-label>
                  <v-text-field variant="outlined" hide-details color="primary" v-model="userEmail"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-label class="font-weight-bold mb-1">Name</v-label>
                  <v-text-field variant="outlined" hide-details color="primary" v-model="userName"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-label class="font-weight-bold mb-1">Password</v-label>
                  <v-text-field variant="outlined" type="password"  hide-details color="primary" v-model="userPassword"></v-text-field>
                </v-col>
                <v-col cols="12" class="pt-0">
                  <v-btn color="primary" size="large" block flat @click="signUp()">Sign in</v-btn>
                </v-col>
              </v-row>
            </v-card-item>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>