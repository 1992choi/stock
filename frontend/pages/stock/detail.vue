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
              <h4 class="fw-bold py-3 mb-4">공모주 상세</h4>

              <!-- Basic Bootstrap Table -->
              <div class="card-body">
                <form>
                  <div class="row mb-3">
                    <label class="col-sm-2 col-form-label" for="basic-default-name">공모주</label>
                    <div class="col-sm-10">
<!--                      <input type="text" class="form-control" v-model="stockName"/>-->
                      <input type="text" class="form-control" v-model="stock.stockName"/>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <label class="col-sm-2 col-form-label" for="basic-default-name">공모일</label>
                    <div class="col-sm-10">
<!--                      <input type="text" class="form-control" v-model="subscriptDate"/>-->
                      <input type="text" class="form-control" v-model="stock.subscriptDate"/>
                    </div>
                  </div>
                  <div class="row mb-3">
                    <label class="col-sm-2 col-form-label" for="basic-default-name">상장일</label>
                    <div class="col-sm-10">
<!--                      <input type="text" class="form-control" v-model="listingDate"/>-->
                      <input type="text" class="form-control" v-model="stock.listingDate"/>
                    </div>
                  </div>
                  <div class="row justify-content-end text-end">
                    <div class="col-sm-10">
                      <button type="button" class="btn btn-primary" @click="edit()">수정</button>
                      <button type="button" class="btn btn-danger" @click="remove()">삭제</button>
                    </div>
                  </div>
                </form>
              </div>
              <!--/ Basic Bootstrap Table -->
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
      // stockId: '',
      // stockName: '',
      // subscriptDate: '',
      // listingDate: ''
      stock: {}
    }
  },
  methods: {
    async getStock() {
      const stockId = this.$route.query.stockId;
      const response = await axios.get('http://localhost:8080/api/stocks/' + stockId);
      // this.stockName = response.data.stockName;
      // this.subscriptDate = response.data.subscriptDate;
      // this.listingDate = response.data.listingDate;
      this.stock = response.data.data;
    },
    async edit() {
      const stockId = this.$route.query.stockId;
      await axios.put('http://localhost:8080/api/stocks/' + stockId, {
        // stockName: this.stockName,
        // subscriptDate: this.subscriptDate,
        // listingDate: this.listingDate
        stockName: this.stock.stockName,
        subscriptDate: this.stock.subscriptDate,
        listingDate: this.stock.listingDate
      }).then((response) => {
        if (response.status === 200) {
          if (response.data.status == 'success') {
            alert("수정 성공");
            location.href = 'list'
          } else {
            alert(response.data.message);
          }
        }
      }).catch((error) => {
        console.log(error.response)
        alert("수정 실패");
      });
    },
    async remove() {
      const stockId = this.$route.query.stockId;
      await axios.delete('http://localhost:8080/api/stocks/' + stockId)
          .then((response) => {
            if (response.status === 200) {
              if (response.data.status == 'success') {
                alert("삭제 성공");
                location.href = 'list'
              } else {
                alert(response.data.message);
              }
            }
          }).catch((error) => {
            console.log(error.response)
            alert("삭제 실패");
          });
    }
  },
  created() {
    this.getStock();
  },
}
</script>