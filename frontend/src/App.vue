<template>
  <v-app>
    <v-main>
      <HangManGame/>
    </v-main>
  </v-app>
</template>

<script>

import HangManGame from "@/components/HangManGame";

export default {
  name: 'App',

  components: {
    HangManGame,
  },
}
</script>

<style>

:root {
  font-family: 'Roboto', sans-serif;
  overflow: auto;
}

</style>
