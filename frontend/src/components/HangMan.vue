<template>
  <div class="main_container">
    <div class="draw_container card">
      <DrawGallows ref="drawGallows" :state="state"/>
    </div>
    <div class="menu_container card">
      <div class="letter_container card">
        <div class="letter_sequence">
          <span class="letter" v-for="(letter, index) in letters" :key="index">{{ letter }}</span>
        </div>
        <div class="already_tried">
          <span v-for="(letter, index) in alreadyTried" :key="index">{{ letter }}</span>
        </div>
      </div>
      <div class="input_menu">
        <div class="card input_letter_container">
          <input class="input_letter" maxlength="1" v-model="input" onkeydown="return /[a-z]/i.test(event.key)">
          <div :class="input ? 'button' : 'disabled button'" @click="sendLetter"><span>Enter letter</span></div>
        </div>
        <div class="button" @click="reset"><span>RESET</span></div>
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
      url: 'http://localhost:8082/Hangman',
      input: null,

      state: 0,
      letters: [],
      alreadyTried: [],
    }
  },
  mounted() {
    this.reset()
  },
  methods: {
    reset() {
      axios.post(this.url).then(resp => this.fetchData(resp)).catch(error => console.log(error))
      this.$refs.drawGallows.redraw()
    },
    sendLetter() {
      axios.get(this.url + '?letter=' + this.input).then(resp => this.fetchData(resp)).catch(error => console.log(error))
    },
    fetchData(resp) {
      this.letters = resp.data.letters
      this.state = resp.data.state
      this.alreadyTried = resp.data.alreadyTried
    }
  }
}
</script>

<style scoped>

.draw_container {
  display: flex;
  justify-content: center;
  flex-grow: 1;
}

.main_container {
  display: flex;
  flex-direction: row;
  justify-content: start;

  padding: 0 20px;
}

.menu_container {
  display: flex;
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

.already_tried {
  display: flex;
  flex-direction: row;
}

.letter_container {
  display: flex;
  flex-direction: column;
}

</style>