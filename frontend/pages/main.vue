<template>
  <div class="container-xxl flex-grow-1 container-p-y">
    <div class="row">
      <!-- Transactions -->
      <div class="col-md-12 col-lg-12 order-2 mb-4">
        <div class="card h-100">
          <div class="card-header d-flex align-items-center justify-content-between">
            <h5 class="card-title m-0 me-2">현재가</h5>
          </div>
          <div class="card-body">
            <ul class="p-0 m-0">
              <li class="d-flex mb-4 pb-1">
                <div class="avatar flex-shrink-0 me-3">
                  <img src="../assets/img/icons/unicons/cc-success.png" alt="User" class="rounded"/>
                </div>
                <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                  <div class="me-2">
                    <small class="text-muted d-block mb-1">KOSPI</small>
                    <h6 class="mb-0">삼성전자</h6>
                  </div>
                  <div class="user-progress d-flex align-items-center gap-1">
                    <h6 class="mb-0">73,500</h6>
                    <span class="text-muted">+0.68%</span>
                  </div>
                </div>
              </li>
              <li class="d-flex mb-4 pb-1">
                <div class="avatar flex-shrink-0 me-3">
                  <img src="../assets/img/icons/unicons/cc-success.png" alt="User" class="rounded"/>
                </div>
                <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                  <div class="me-2">
                    <small class="text-muted d-block mb-1">KOSPI</small>
                    <h6 class="mb-0">LG전자</h6>
                  </div>
                  <div class="user-progress d-flex align-items-center gap-1">
                    <h6 class="mb-0">96,500</h6>
                    <span class="text-muted">+1.79%</span>
                  </div>
                </div>
              </li>
              <li class="d-flex mb-4 pb-1">
                <div class="avatar flex-shrink-0 me-3">
                  <img src="../assets/img/icons/unicons/cc-success.png" alt="User" class="rounded"/>
                </div>
                <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                  <div class="me-2">
                    <small class="text-muted d-block mb-1">KOSPI</small>
                    <h6 class="mb-0">NAVER</h6>
                  </div>
                  <div class="user-progress d-flex align-items-center gap-1">
                    <h6 class="mb-0">213,500</h6>
                    <span class="text-muted">+1.84%</span>
                  </div>
                </div>
              </li>
              <li class="d-flex mb-4 pb-1">
                <div class="avatar flex-shrink-0 me-3">
                  <img src="../assets/img/icons/unicons/cc-warning.png" alt="User" class="rounded"/>
                </div>
                <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                  <div class="me-2">
                    <small class="text-muted d-block mb-1">KOSPI</small>
                    <h6 class="mb-0">카카오</h6>
                  </div>
                  <div class="user-progress d-flex align-items-center gap-1">
                    <h6 class="mb-0">51,800</h6>
                    <span class="text-muted">-3.84%</span>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!--/ Transactions -->
    </div>
  </div>
</template>

<script>
export default {
  head: {
    script: [
      { src: "../assets/vendor/libs/jquery/jquery.js" },
      { src: "../assets/vendor/js/bootstrap.js" },
      { src: "../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js" },
      { src: "../assets/vendor/js/menu.js" },
      { src: "../assets/vendor/libs/apex-charts/apexcharts.js" },
      { src: "../assets/js/main.js" },
      { src: "../assets/js/dashboards-analytics.js" }
    ]
  }
}
</script>