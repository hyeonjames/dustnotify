<template>
  <div id="app">
    <h2>시도별 미세먼지 데이터</h2>
    <korea-map v-model="selected" v-bind:dust="today"/>
    <line-chart/>
    <station-list region="11"/>
    <line-chart-viewer />
    <!-- <line-chart/> -->
  </div>
</template>

<script>
//import Map from './components/Map.vue'
import com from './com.js'
import LineChart from './components/LineChart.vue'
import KoreaMap from './components/KoreaMap.vue'
import StationList from './components/StationList.vue'
import LineChartViewer from './containers/LineChartViewer.vue'
// import LineChart from './components/LineChart.vue'

/*
{ text:"서울", value: "seoul" },
                                { text:"부산", value: "busan" },
                                { text:"대구", value: "daegu" },
                                { text:"인천", value: "incheon" },
                                { text:"광주", value: "gwangju" },
                                { text:"대전", value: "daejeon" },
                                { text:"울산", value: "ulsan" },
                                { text:"경기", value: "gyeonggi" },
                                { text:"강원", value: "gangwon"  },
                                { text:"충북", value: "chungbuk"},
                                { text:"충남", value: "chungnam"},
                                { text:"전북", value: "jeonbuk" },
                                { text:"전남", value: "jeonnam" },
                                { text:"경북", value: "gyeongbuk" },
                                { text:"경남", value: "gueongnam" },
                                { text:"제주", value: "jeju" },
                                { text:"세종", value: "sejong"}         */
const regionDict = {
    '서울' : 'seoul',
    '부산' : 'busan',
    '대구' : 'daegu',
    '인천' : 'incheon',
    '광주': 'gwangju',
    '대전' : 'daejeon',
    '울산' : 'ulsan',
    '경기' : 'gyeonggi',
    '강원' : 'gangwon',
    '충북' : 'chungbuk',
    '충남' : 'chungnam',
    '전북'  :'jeonbuk',
    '전남' : 'jeonnam',
    '경북' : 'gyeongbuk',
    '경남' : 'gyuongnam',
    '제주' : 'jeju',
    '세종' : 'sejoung'
};
export default {
  name: 'app',
  components: {
    LineChartViewer,
    KoreaMap,
    StationList
  },
  data() {
    return {
      daily : [],
      dailyDict : {},
      today : {}, 
      selected : ['seoul'],
      mycity : 'seoul'
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
    });
    if(navigator && navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(({coords})=>{
        let daum = window.daum;
        var geocoder = new daum.maps.services.Geocoder();
        geocoder.coord2Address(coords.longitude,coords.latitude, (r, status)=>{
          if(status == daum.maps.services.Status.OK) {
            if( r.length > 0) {
              let name = r[0].address.region_1depth_name;
              if(regionDict[name]) {
                this.mycity = regionDict[name]
              }
            }

          }
        })
      })
    }
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
