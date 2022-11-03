<template>
  <div class="splash">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "ScreenSplash",
}
</script>

<style scoped>
.splash {
  position: fixed;
  top: 0;
  left: 0;

  height: 100vh;
  width: 100vw;

  background-color: #9d9d9d;

  opacity: 0.8;
}
</style>