<template>
  <div id="app">
    <h2>시도별 미세먼지 데이터</h2>
    <korea-map v-model="selected" v-bind:dust="today"/>
    <line-chart/>
    <station-list region="11"/>
  </div>
</template>

<script>
//import Map from './components/Map.vue'
import com from './com.js'
import LineChart from './components/LineChart.vue'
import KoreaMap from './components/KoreaMap.vue'
import StationList from './components/StationList.vue'
export default {
  name: 'app',
  components: {
    LineChart,
    KoreaMap,
    StationList
  },
  data() {
    return {
      daily : [],
      dailyDict : {},
      today : {}, 
      selected : ['seoul']
    }
  },
  created() {
    com.get('api/stat/get/daily')
    .then ((r)=>{
      var d = {}
      for(var index in r){
        var e  = r[index]
        
        d[e.date.substring(0,10)] = e;
      }
      this.dailyDict = d;
      this.daily = r;
      this.today = r[r.length-1];
    })
  },
  methods : {
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
