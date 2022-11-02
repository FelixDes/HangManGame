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
      vueCanvas: null
    }
  },
  mounted() {
    let canvas = this.$refs.canvas
    this.vueCanvas = canvas.getContext("2d")
    canvas.height = 1000
    canvas.width = 1000
    this.vueCanvas.beginPath()
    this.drawGallows()
  },
  methods: {
    drawGallows() {
      let ctx = this.vueCanvas

      ctx.moveTo(100, 900)
      ctx.lineTo(500, 900)

      ctx.moveTo(300,900)
      ctx.lineTo(300, 100)

      ctx.moveTo(300,300)
      ctx.lineTo(500, 100)

      ctx.moveTo(250,100)
      ctx.lineTo(750, 100)

      ctx.moveTo(700, 100)
      ctx.lineTo(700, 300)

      ctx.lineWidth = 10
      ctx.stroke()
    }
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