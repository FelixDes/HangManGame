<template>
  <v-app>
    <v-main>
      <HangMan/>
    </v-main>
  </v-app>
</template>

<script>
import HangMan from "@/components/HangMan";

export default {
  name: 'App',

  components: {
    HangMan,
  },

  data: () => ({
    //
  }),
}
</script>

<style>

:root {
  font-family: 'Roboto', sans-serif;
  overflow: auto;
}

</style>
