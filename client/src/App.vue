<template>
  <div id="app">
    <h1>도시별 미세먼지 데이터</h1>
    <line-chart/>
  </div>
</template>

<script>
//import Map from './components/Map.vue'
import LineChart from './components/LineChart.vue'

export default {
  name: 'app',
  components: {
    LineChart
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
