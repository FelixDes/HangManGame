<template>
  <canvas ref="canvas" class="canvas"></canvas>
</template>

<script>
export default {
  name: "DrawGallows",
  props: ['state'],
  data() {
    return {
      ctx: null,
    }
  },
  mounted() {
    let canvas = this.$refs.canvas
    this.ctx = canvas.getContext("2d")
    canvas.height = 1000
    canvas.width = 1000
    this.drawGallows()
  },
  methods: {
    clearCanvas() {
      this.ctx.clearRect(0, 0, this.$refs.canvas.width, this.$refs.canvas.height);
    },

    redraw() {
      this.clearCanvas()
      this.drawGallows()
    },

    drawGallows() {
      this.ctx.beginPath()

      this.ctx.moveTo(100, 900)
      this.ctx.lineTo(500, 900)

      this.ctx.moveTo(300, 900)
      this.ctx.lineTo(300, 100)

      this.ctx.moveTo(300, 300)
      this.ctx.lineTo(500, 100)

      this.ctx.moveTo(250, 100)
      this.ctx.lineTo(750, 100)

      this.ctx.moveTo(700, 100)
      this.ctx.lineTo(700, 400)

      this.ctx.lineWidth = 10
      this.ctx.stroke()
    },

    drawForState() {
      let s = this.state

      this.ctx.beginPath()

      if (s > 0) {
        this.ctx.arc(700, 425, 25, 0, 2 * Math.PI);
      }
      if (s > 1) {
        this.ctx.moveTo(700, 450)
        this.ctx.lineTo(700, 550)
      }
      if (s > 2) {
        this.ctx.moveTo(700, 450)
        this.ctx.lineTo(670, 480)
      }
      if (s > 3) {
        this.ctx.moveTo(700, 450)
        this.ctx.lineTo(730, 480)
      }
      if (s > 4) {
        this.ctx.moveTo(700, 550)
        this.ctx.lineTo(670, 580)
      }
      if (s > 5) {
        this.ctx.moveTo(700, 550)
        this.ctx.lineTo(730, 580)
      }

      this.ctx.lineWidth = 10
      this.ctx.stroke()
    },
  },
  watch: {
    state() {
      this.drawForState()
    }
  }
}
</script>

<style scoped>

.canvas {
  width: 1000px;
  height: 1000px;
  border: 2px solid black;
}

</style>