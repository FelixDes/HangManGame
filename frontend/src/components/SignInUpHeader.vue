<template>
  <v-toolbar color="gray">
    <v-toolbar-title>Hangman</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-dialog v-if="id"
              v-model="signUpMenu"
              width="500"
    >
<!--      <template v-slot:activator="{ on, attrs }">-->
<!--        <v-btn @click="signUpMenu = !signInMenu"-->
<!--               v-bind="attrs"-->
<!--               v-on="on">-->
<!--          Sign Up-->
<!--        </v-btn>-->
<!--      </template>-->


<!--      <v-card>-->
<!--        <v-card-title>-->
<!--          Sign Up-->
<!--        </v-card-title>-->

<!--        <v-card-text>-->
<!--          <v-text-field-->
<!--              v-model="signUpModel.name"-->
<!--              label="Name"-->
<!--              required-->
<!--          ></v-text-field>-->

<!--          <v-text-field-->
<!--              v-model="signUpModel.pswd"-->
<!--              :append-icon="showPswd ? 'mdi-eye' : 'mdi-eye-off'"-->
<!--              :type="showPswd ? 'text' : 'password'"-->
<!--              label="Password"-->
<!--              required-->
<!--              @click:append="showPswd = !showPswd"-->
<!--          ></v-text-field>-->
<!--        </v-card-text>-->

<!--        <v-divider></v-divider>-->

<!--        <v-card-actions>-->
<!--          <v-spacer></v-spacer>-->
<!--          <v-btn-->
<!--              color="primary"-->
<!--              text-->
<!--              @click="signIn"-->
<!--          >-->
<!--            Submit-->
<!--          </v-btn>-->
<!--        </v-card-actions>-->
<!--      </v-card>-->
    </v-dialog>


    <v-dialog
        v-else
        v-model="signInMenu"
        width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn @click="signInMenu = !signInMenu"
               v-bind="attrs"
               v-on="on">
          Sign In
        </v-btn>
      </template>


      <v-card>
        <v-card-title>
          Sign In
        </v-card-title>

        <v-card-text>
          <v-text-field
              v-model="signInModel.name"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              v-model="signInModel.pswd"
              :append-icon="showPswd ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPswd ? 'text' : 'password'"
              label="Password"
              required
              @click:append="showPswd = !showPswd"
          ></v-text-field>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn @click="signIn">
            Submit
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-toolbar>
</template>

<script>
import axios from "axios";
import PATHS from "@/PATHS";
// import md5 from "js-md5"

export default {
  name: "SignInUpHeader",
  data() {
    return {
      signInMenu: false,
      signInModel: {
        name: null,
        pswd: null,
      },
      signUpMenu: false,
      signUpModel: {
        name: null,
        pswd: null,
      },
      showPswd: false,
    }
  },
  props: ["id"],
  mounted() {
  },
  methods: {
    signIn() {
      if (this.signInModel.name && this.signInModel.pswd) {
        let _data = this.signInMenu
        // _data.pswd = md5(_data.pswd)
        console.log(_data)
        axios.post(PATHS.urlSignIn, _data).then(res => console.log(res))
        this.signInMenu = false
      }
    },
    signUp() {
      this.signUpMenu = false
    },
  }
}
</script>

<style scoped>

</style>