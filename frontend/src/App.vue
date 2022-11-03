<template>
  <HangMan/>
</template>

<script>

import HangMan from "@/components/HangMan";

export default {
  name: 'App',
  components: {
    HangMan
  }
}
</script>

<style>

:root {
  font-family: 'Roboto', sans-serif;
}


.card {
  padding: 10px;
  margin: 8px;

  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  border-radius: 4px;
}

.button {
  height: fit-content;

  margin: 5px;
  padding: 2px 10px;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  background-color: #d5d5d5;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  border-radius: 4px;

  transition: 0.5s;
  pointer-events: all;
}

.button:hover {
  background-color: #bdbdbd;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.button:active {
  background-color: #d5d5d5;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.disabled {
  pointer-events: none;
  background-color: #9d9d9d;
}

</style>
