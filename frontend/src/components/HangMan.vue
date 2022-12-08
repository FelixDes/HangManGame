<template>
  <div>
<!--    <SignInUpHeader @onIdChanged="onIdChanged"></SignInUpHeader>-->
    <HangManGame></HangManGame>
  </div>
</template>

<script>
// import SignInUpHeader from "@/components/SignInUpHeader";
import HangManGame from "@/components/HangManGame";

export default {
  name: "HangMan",
  components: {HangManGame,},
  // data() {
  //   return {
  //     id: null,
  //   }
  // },
  mounted() {
    // this.checkAndSetId()
  },
  methods: {
    // checkAndSetId() {
    //   let _id = sessionStorage.getItem("id")
    //   if (_id) {
    //     // TODO Check: is id registered?
    //     this.id = _id
    //   }
    // },
    // onIdChanged(_id) {
    //   sessionStorage.setItem("id", _id)
    //   this.id = _id
    // },
  }
}
</script>

<style scoped>

</style>