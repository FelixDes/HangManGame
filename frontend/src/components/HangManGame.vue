<template>
  <div class="main_container">
    <v-card elevation="4" outlined class="draw_container mr-2">
      <DrawGallows ref="drawGallows" :deathState="deathState"/>
    </v-card>
    <v-card elevation="4" outlined class="menu_container pa-2">
      <v-card class="letter_container mb-1 pa-2" elevation="2" outlined>
        <div class="letter_sequence">
          <span class="letter" v-for="(letter, index) in letters" :key="index">{{ letter }}</span>
        </div>
        <div class="already_tried  mb-1">
          <span v-for="(letter, index) in alreadyTried" :key="index">{{ letter }}</span>
        </div>
      </v-card>
      <v-card class="input_menu pa-2">
        <v-card class="input_letter_container">
          <v-card-title>
            <v-text-field
                label="Letter"
                :rules="rules"
                hide-details="auto"
                v-model="input"
                v-on:keyup.enter="sendLetter"
                maxlength="1"
            ></v-text-field>
          </v-card-title>

          <v-card-actions>
            <v-btn :disabled="!input || input == ' '" @click="sendLetter"><span>Enter letter</span></v-btn>
          </v-card-actions>
        </v-card>
        <v-btn class="ml-2" @click="reset"><span>RESET</span></v-btn>
      </v-card>
    </v-card>
  </div>
  <ScreenSplash class="splash" v-if="winFlag === 'WIN' || winFlag === 'FAIL'">
    <template v-if="winFlag === 'WIN'">
      <span>You won!</span>
      <v-btn @click="reset"><span>Restart?)</span></v-btn>
    </template>
    <template v-else-if="winFlag === 'FAIL'">
      <span>You failed!</span>
      <v-btn @click="reset"><span>Restart?</span></v-btn>
    </template>
  </ScreenSplash>
</template>

<script>
import axios from "axios";
import PATHS from "@/PATHS";
import ScreenSplash from "@/components/ScreenSplash";
import DrawGallows from "@/components/DrawGallows";

export default {
  name: "HangManGame",
  components: {ScreenSplash, DrawGallows},
  data() {
    return {
      id: null,
      input: null,

      deathState: 0,
      letters: [],
      alreadyTried: [],
      winFlag: null,

      splash: false,

      rules: [
        value => !!value || 'Required.',
        value => (!!value && /[a-z]/i.test(value)) || 'Only english letters',
      ],
    }
  },
  mounted() {
    this.getId()
  },
  methods: {
    reset() {
      axios.get(PATHS.urlHangman + "?id=" + this.id + "&reset=true").then(resp => this.fetchData(resp)).catch(error => console.log(error))
      this.$refs.drawGallows.redraw()
      this.splash = false
    },
    sendLetter() {
      if (this.winFlag !== 'IN_PROCESS') {
        return
      }
      axios.get(PATHS.urlHangman + "?id=" + this.id + '&letter=' + this.input).then(resp => this.fetchData(resp)).catch(error => console.log(error))
      this.input = null
    },

    getId() {
      if (this.id === null) {
        axios.get(PATHS.urlId).then(resp => {
          this.id = resp.data
          this.reset()
        }).catch(error => console.log(error))
      } else {
        this.reset()
      }
    },
    fetchData(resp) {
      this.letters = resp.data.letters
      this.deathState = resp.data.deathState
      this.alreadyTried = resp.data.alreadyTried
      this.winFlag = resp.data.winFlag
    },
  },
}
</script>

<style scoped>
.main_container {
  display: flex;
  flex-direction: row;
  justify-content: start;
  padding: 10px;
}

.draw_container {
  display: flex;
  justify-content: center;
  flex-grow: 1;
}

.menu_container {
  display: flex;
  flex-direction: column;
}

.input_menu {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  align-items: start;
}

.input_letter_container {
  display: flex;
  flex-direction: column;
}

.letter_sequence {
  height: 20px;
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

.splash {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: xx-large;
}
</style>