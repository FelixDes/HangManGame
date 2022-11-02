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
          <div :class="input? 'button':'disabled button'" @click="sendLetter">Enter letter</div>
        </div>
        <div class="button" @click="reset">RESET</div>
      </div>
    </div>
  </div>
</template>

<script>
import DrawGallows from "@/components/DrawGallows";
import axios from "axios";

export default {
  name: "HangMan",
  components: {DrawGallows},
  data() {
    return {
      input: null,
      state: 0,

      letters: []
    }
  },
  methods: {
    reset() {
      axios.post('/reset').then(() => this.clearAll()).catch(error => console.log(error))

    },
    sendLetter() {
      axios.post('/push_letter', this.input).then(resp => this.fetchData(resp)).catch(error => console.log(error))
    },
    fetchData(resp) {
      this.letters = resp.letters
    },
    clearAll() {
      this.input = null
      this.state = 0

      this.letters = []
    }
  }
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