<template>
  <div>
    <div class="main_container">
      <div class="draw_container card">
        <DrawGallows ref="drawGallows" :deathState="deathState"/>
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
            <input class="input_letter" maxlength="1" v-model="input" onkeydown="return /[a-z]/i.test(event.key)"
                   v-on:keyup.enter="sendLetter">
            <div :class="input ? 'button' : 'disabled button'" @click="sendLetter"><span>Enter letter</span></div>
          </div>
          <div class="button" @click="reset"><span>RESET</span></div>
        </div>
      </div>
    </div>
    <ScreenSplash class="splash" v-if="winFlag === 'WIN' || winFlag === 'FAIL'">
      <template v-if="winFlag === 'WIN'">
        <span>You won!</span>
        <div class="button" @click="reset"><span>Restart?)</span></div>
      </template>
      <template v-else-if="winFlag === 'FAIL'">
        <span>You failed!</span>
        <div class="button" @click="reset"><span>Restart?</span></div>
      </template>
    </ScreenSplash>
  </div>
</template>

<script>
import DrawGallows from "@/components/DrawGallows";
import axios from "axios";
import ScreenSplash from "@/components/ScreenSplash";

export default {
  name: "HangMan",
  components: {ScreenSplash, DrawGallows},
  data() {
    return {
      id: 1,

      urlHangman: 'http://localhost:8082/Hangman',
      urlId: 'http://localhost:8082/GetId',
      input: null,

      deathState: 0,
      letters: [],
      alreadyTried: [],
      winFlag: [],

      splash: false
    }
  },
  mounted() {
    this.getId()
  },
  methods: {
    reset() {
      axios.get(this.urlHangman + "?id=" + this.id + "&reset=true").then(resp => this.fetchData(resp)).catch(error => console.log(error))
      this.$refs.drawGallows.redraw()
      this.splash = false
    },
    sendLetter() {
      if (this.winFlag !== 'IN_PROCESS') {
        return
      }
      axios.get(this.urlHangman + "?id=" + this.id + '&letter=' + this.input).then(resp => this.fetchData(resp)).catch(error => console.log(error))
      this.input = null
    },
    getId() {
      let id = this.getCookie('id');
      // if (!id) {
        axios.get(this.urlId).then(resp => id = resp).catch(error => console.log(error))
        this.setCookie('id', id)
      // }
      this.id = id
      this.reset()
    },
    fetchData(resp) {
      this.letters = resp.data.letters
      this.deathState = resp.data.deathState
      this.alreadyTried = resp.data.alreadyTried
      this.winFlag = resp.data.winFlag
    },
    setCookie(cname, cvalue, exdays) {
      const d = new Date();
      d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
      let expires = "expires=" + d.toUTCString();
      document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    },
    getCookie(cname) {
      let name = cname + "=";
      let decodedCookie = decodeURIComponent(document.cookie);
      let ca = decodedCookie.split(';');
      for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
          c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
          return c.substring(name.length, c.length);
        }
      }
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

.splash {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: xx-large;
}

</style>