<template>
  <div class="main_container">
    <div class="draw_container card">
      <DrawGallows :state="state"/>
    </div>
    <div class="menu_container card">
      <div class="card letter_sequence">
        <div class="letter" v-for="(letter, index) in letters" :key="index">
          {{ letter }}
        </div>
      </div>
      <div class="input_menu">
        <div class="card input_letter_container">
          <input class="input_letter" maxlength="1" v-model="input" onkeydown="return /[a-z]/i.test(event.key)">
          <div class="button">ENTER</div>
        </div>
        <div class="button">RESET</div>
      </div>
    </div>
  </div>
</template>

<script>
import DrawGallows from "@/components/DrawGallows";

export default {
  name: "HangMan",
  components: {DrawGallows},
  data() {
    return {
      input: null,
      state: 0,
      letters: ["a", "b", "", "d"]
    }
  },
}
</script>

<style scoped>

.draw_container {
  display: flex;
  justify-content: center;
}

.main_container {
  display: flex;
  flex-direction: row;
  justify-content: start;

  padding: 0 20px;
}

.menu_container {
  display: flex;
  flex-grow: 1;
  flex-direction: column;
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

  background-color: #d5d5d5;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  border-radius: 4px;

  transition: 0.3s;
}

.button:hover {
  background-color: #bdbdbd;
  box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

.input_menu {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
}

.input_letter_container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.input_letter {
  text-align: center
}

.letter_sequence {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.letter {
  width: 20px;

  margin: 0 2px;

  text-align: center;
  border-bottom: solid gray;
}

</style>