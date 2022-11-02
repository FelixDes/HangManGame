<template>
  <canvas ref="canvas" class="canvas"></canvas>
</template>

<script>
export default {
  name: "DrawGallows",
  props: {
    state: Number
  },
  data() {
    return {
      vueCanvas: null,
    }
  },
  mounted() {
    let canvas = this.$refs.canvas
    this.vueCanvas = canvas.getContext("2d")
    canvas.height = 1000
    canvas.width = 1000
    this.drawGallows()
    this.drawForState()
  },
  methods: {
    drawGallows() {
      let ctx = this.vueCanvas

      this.vueCanvas.beginPath()

      ctx.moveTo(100, 900)
      ctx.lineTo(500, 900)

      ctx.moveTo(300, 900)
      ctx.lineTo(300, 100)

      ctx.moveTo(300, 300)
      ctx.lineTo(500, 100)

      ctx.moveTo(250, 100)
      ctx.lineTo(750, 100)

      ctx.moveTo(700, 100)
      ctx.lineTo(700, 400)

      ctx.lineWidth = 10
      ctx.stroke()
    },

    drawForState() {
      let s = this.state
      let ctx = this.vueCanvas

      this.vueCanvas.beginPath()

      if (s > 0) {
        ctx.arc(700, 425, 25, 0, 2 * Math.PI);
      }
      if (s > 1) {
        ctx.moveTo(700, 450)
        ctx.lineTo(700, 550)
      }
      if (s > 2) {
        ctx.moveTo(700, 450)
        ctx.lineTo(670, 480)
      }
      if (s > 3) {
        ctx.moveTo(700, 450)
        ctx.lineTo(730, 480)
      }
      if (s > 4) {
        ctx.moveTo(700, 550)
        ctx.lineTo(670, 580)
      }
      if (s > 5) {
        ctx.moveTo(700, 550)
        ctx.lineTo(730, 580)
      }

      ctx.lineWidth = 10
      ctx.stroke()
    },
  },
}
</script>

<style scoped>

.canvas {
  width: 1000px;
  height: 1000px;
  border: 2px solid black;
}

</style>