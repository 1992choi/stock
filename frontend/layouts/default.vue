<script setup lang="ts">

const title = ref("Modernize - Nuxt3 Typescript based Free Admin Dashboard Template");
useHead({
  meta: [{ content: title }],
  titleTemplate: (titleChunk) => {
    return titleChunk
        ? `${titleChunk} - Nuxt3 Typescript based Free Admin Dashboard Template`
        : "Modernize - Nuxt3 Typescript based Free Admin Dashboard Template";
  },
});
</script>

<template>
  <v-locale-provider >
    <v-app>
      <LayoutFullMain/>
      <v-main>
        <v-container fluid class="page-wrapper">
          <div class="maxWidth">
            <NuxtPage  />
          </div>
        </v-container>
      </v-main>
    </v-app>
  </v-locale-provider>
</template>

<!--<template>-->
<!--  <div>-->
<!--    <div class="layout-wrapper layout-content-navbar">-->
<!--      <div class="layout-container">-->
<!--        &lt;!&ndash; Menu &ndash;&gt;-->
<!--        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">-->
<!--          <div class="app-brand demo">-->
<!--            <span class="fw-bolder">Stock</span>-->
<!--          </div>-->

<!--          <ul class="menu-inner py-1">-->
<!--            &lt;!&ndash; Dashboard &ndash;&gt;-->
<!--            <li class="menu-item active">-->
<!--              <a class="menu-link">-->
<!--                <i class="menu-icon tf-icons bx bx-home-circle"></i>-->
<!--                <nuxt-link to="/main">Dashboard</nuxt-link>-->
<!--              </a>-->
<!--            </li>-->

<!--            <li class="menu-item">-->
<!--              <a class="menu-link">-->
<!--                <i class="menu-icon tf-icons bx bx-home-circle"></i>-->
<!--                <nuxt-link to="/stock/list">공모주</nuxt-link>-->
<!--              </a>-->
<!--            </li>-->
<!--          </ul>-->
<!--        </aside>-->
<!--        &lt;!&ndash; / Menu &ndash;&gt;-->

<!--        &lt;!&ndash; Layout container &ndash;&gt;-->
<!--        <div class="layout-page">-->
<!--          &lt;!&ndash; Navbar &ndash;&gt;-->
<!--          <nav class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme" id="layout-navbar">-->
<!--            <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">-->
<!--              <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">-->
<!--                <i class="bx bx-menu bx-sm"></i>-->
<!--              </a>-->
<!--            </div>-->

<!--            <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">-->
<!--              <ul class="navbar-nav flex-row align-items-center ms-auto">-->
<!--                &lt;!&ndash; User &ndash;&gt;-->
<!--                <li class="nav-item navbar-dropdown dropdown-user dropdown">-->
<!--                  <a class="nav-link dropdown-toggle hide-arrow" data-bs-toggle="dropdown" @click="showArea('user')">-->
<!--                    <div class="avatar avatar-online">-->
<!--                      <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle"/>-->
<!--                    </div>-->
<!--                  </a>-->
<!--                  <ul id="user" class="dropdown-menu dropdown-menu-end">-->
<!--                    <li>-->
<!--                      <a class="dropdown-item" href="#">-->
<!--                        <div class="d-flex">-->
<!--                          <div class="flex-shrink-0 me-3">-->
<!--                            <div class="avatar avatar-online">-->
<!--                              <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle"/>-->
<!--                            </div>-->
<!--                          </div>-->
<!--                          <div class="flex-grow-1">-->
<!--                            <span class="fw-semibold d-block">{{ user.name }}</span>-->
<!--                            <small class="text-muted">Admin</small>-->
<!--                          </div>-->
<!--                        </div>-->
<!--                      </a>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <div class="dropdown-divider"></div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <a class="dropdown-item" href="#">-->
<!--                        <i class="bx bx-user me-2"></i>-->
<!--                        <span class="align-middle">My Profile</span>-->
<!--                      </a>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <a class="dropdown-item" href="#">-->
<!--                        <i class="bx bx-cog me-2"></i>-->
<!--                        <span class="align-middle">Settings</span>-->
<!--                      </a>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <a class="dropdown-item" href="#">-->
<!--                          <span class="d-flex align-items-center align-middle">-->
<!--                            <i class="flex-shrink-0 bx bx-credit-card me-2"></i>-->
<!--                            <span class="flex-grow-1 align-middle">Billing</span>-->
<!--                            <span class="flex-shrink-0 badge badge-center rounded-pill bg-danger w-px-20 h-px-20">4</span>-->
<!--                          </span>-->
<!--                      </a>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <div class="dropdown-divider"></div>-->
<!--                    </li>-->
<!--                    <li>-->
<!--                      <a class="dropdown-item">-->
<!--                        <i class="bx bx-power-off me-2"></i>-->
<!--                        <span class="align-middle">Log Out</span>-->
<!--                      </a>-->
<!--                    </li>-->
<!--                  </ul>-->
<!--                </li>-->
<!--                &lt;!&ndash;/ User &ndash;&gt;-->
<!--              </ul>-->
<!--            </div>-->
<!--          </nav>-->
<!--          &lt;!&ndash; / Navbar &ndash;&gt;-->

<!--          &lt;!&ndash; Content wrapper &ndash;&gt;-->
<!--          <div class="content-wrapper">-->
<!--            &lt;!&ndash; Content &ndash;&gt;-->
<!--            <slot />-->
<!--            &lt;!&ndash; / Content &ndash;&gt;-->
<!--            <footer class="content-footer footer bg-footer-theme">-->
<!--              <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">-->
<!--                <div class="mb-2 mb-md-0">-->
<!--                  © CHOI-->
<!--                </div>-->
<!--              </div>-->
<!--            </footer>-->
<!--            <div class="content-backdrop fade"></div>-->
<!--          </div>-->
<!--          &lt;!&ndash; Content wrapper &ndash;&gt;-->

<!--        </div>-->
<!--        &lt;!&ndash; / Layout page &ndash;&gt;-->
<!--      </div>-->
<!--      &lt;!&ndash; Overlay &ndash;&gt;-->
<!--      <div class="layout-overlay layout-menu-toggle"></div>-->
<!--    </div>-->
<!--    &lt;!&ndash; / Layout wrapper &ndash;&gt;-->
<!--  </div>-->
<!--</template>-->
<!--<script>-->
<!--import { useAuthStore } from '~/stores/auth'-->

<!--export default defineComponent({-->
<!--    methods: {-->
<!--        showArea(tagId) {-->
<!--            const tag = document.getElementById(tagId);-->
<!--            if (tag.style.display != 'block') {-->
<!--                tag.style.display = 'block'-->
<!--            } else {-->
<!--                tag.style.display = 'none'-->
<!--            }-->
<!--        }-->
<!--    },-->
<!--    setup() {-->
<!--        const store = useAuthStore()-->
<!--        const { user } = store-->
<!--        return {-->
<!--            user-->
<!--        }-->
<!--    },-->
<!--})-->
<!--</script>-->
<!--<style scoped>-->
<!--  .footer {-->
<!--    position: absolute;-->
<!--    right: 5px;-->
<!--    bottom: 5px;-->
<!--  }-->
<!--</style>-->
