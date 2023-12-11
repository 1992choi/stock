<template>
  <div>
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->
        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
            <span class="fw-bolder">Stock</span>
          </div>

          <ul class="menu-inner py-1">
            <!-- Dashboard -->
            <li class="menu-item active">
              <a class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <nuxt-link to="/">Dashboard</nuxt-link>
              </a>
            </li>

            <li class="menu-item">
              <a class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <nuxt-link to="/stock/list">공모주</nuxt-link>
              </a>
            </li>
          </ul>
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
          <!-- Navbar -->
          <nav class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme" id="layout-navbar">
            <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
              <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                <i class="bx bx-menu bx-sm"></i>
              </a>
            </div>

            <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
              <ul class="navbar-nav flex-row align-items-center ms-auto">
                <!-- User -->
                <li class="nav-item navbar-dropdown dropdown-user dropdown">
                  <a class="nav-link dropdown-toggle hide-arrow" data-bs-toggle="dropdown" @click="showArea('user')">
                    <div class="avatar avatar-online">
                      <img src="../../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle"/>
                    </div>
                  </a>
                  <ul id="user" class="dropdown-menu dropdown-menu-end">
                    <li>
                      <a class="dropdown-item" href="#">
                        <div class="d-flex">
                          <div class="flex-shrink-0 me-3">
                            <div class="avatar avatar-online">
                              <img src="../../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle"/>
                            </div>
                          </div>
                          <div class="flex-grow-1">
                            <span class="fw-semibold d-block">CHOI</span>
                            <small class="text-muted">Admin</small>
                          </div>
                        </div>
                      </a>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bx bx-user me-2"></i>
                        <span class="align-middle">My Profile</span>
                      </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bx bx-cog me-2"></i>
                        <span class="align-middle">Settings</span>
                      </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                          <span class="d-flex align-items-center align-middle">
                            <i class="flex-shrink-0 bx bx-credit-card me-2"></i>
                            <span class="flex-grow-1 align-middle">Billing</span>
                            <span class="flex-shrink-0 badge badge-center rounded-pill bg-danger w-px-20 h-px-20">4</span>
                          </span>
                      </a>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="auth-login-basic.html">
                        <i class="bx bx-power-off me-2"></i>
                        <span class="align-middle">Log Out</span>
                      </a>
                    </li>
                  </ul>
                </li>
                <!--/ User -->
              </ul>
            </div>
          </nav>
          <!-- / Navbar -->

          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->
            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4">공모주 목록</h4>

              <!-- Basic Bootstrap Table -->
              <div class="card">
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead>
                    <tr>
                      <th>공모주 #1</th>
                      <th>공모주 #2</th>
                      <th>공모일</th>
                      <th>상장일</th>
                    </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                    <tr v-for="stock in stocks" :key="stock.stockId">
                      <td><nuxt-link :to="{path: '/stock/detail', query:{stockId:stock.stockId}}">{{ stock.stockName }}</nuxt-link></td>
                      <td @click="moveToDetailPage(stock.stockId)">{{ stock.stockName }}</td>
                      <td>{{ stock.subscriptDate }}</td>
                      <td>{{ stock.listingDate }}</td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <!--/ Basic Bootstrap Table -->
              <div class="row justify-content-end text-end pt-5">
                <div class="col-sm-10">
                  <button type="button" class="btn btn-primary" @click="moveToRegistPage()">추가</button>
                </div>
              </div>
            </div>
            <!-- / Content -->
            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>
      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      stocks: {},
    }
  },
  methods: {
    async getStocks() {
      const response = await axios.get('http://localhost:8080/api/stocks');
      this.stocks = response.data.data;
    },
    moveToDetailPage(id) {
      this.$router.push(`${id}`)
    },
    moveToRegistPage() {
      this.$router.push(`regist`)
    },
    showArea(tagId) {
      const tag = document.getElementById(tagId);
      if (tag.style.display != 'block') {
        tag.style.display = 'block'
      } else {
        tag.style.display = 'none'
      }
    }
  },
  created() {
    this.getStocks();
  },
}
</script>

<style lang="scss" scoped>
@import "../../assets/vendor/fonts/boxicons.css";
@import "../../assets/vendor/css/core.css";
@import "../../assets/vendor/css/theme-default.css";
@import "../../assets/css/demo.css";
@import "../../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css";
@import "../../assets/vendor/libs/apex-charts/apex-charts.css";
</style>