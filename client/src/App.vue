<template>
  <div id="app">
    <h2>시도별 미세먼지 데이터</h2>
    <korea-map id="koreaMap" v-model="selected" v-bind:dust="today"/>
      <button type="button" class="btn btn-primary" @click="getStation">측정소 보기</button>
        <selector id="vSelect" v-model="selected"/>
        <button-group id="btnGroup" v-model="btnstate" />
        <line-chart id="lineChart" :city="selected" :state="btnstate"/>
    <!-- <station-list region="11"/> -->
    
    <!-- <line-chart/> -->
      <div class="modal fade" id="stationModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel"> {{eng2kor[currentRegion]}}지역의 측정소</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <station-list :region="currentRegion"/>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
          
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
//import Map from './components/Map.vue'
import com from './com.js'
import $ from 'jquery'
// import LineChart from './components/LineChart.vue'
import KoreaMap from './components/KoreaMap.vue'
import StationList from './components/StationList.vue'
import Selector from './components/Selector.vue'
import LineChart from './components/LineChart.vue'
import ButtonGroup from './components/ButtonGroup.vue'

    const eng2kor = {
        'seoul' : '서울',
        'busan' : '부산',
        'daegu' : '대구',
        'incheon' : '인천',
        'gwangju': '광주',
        'daejeon' : '대전',
        'ulsan' : '울산',
        'gyeonggi' : '경기',
        'gangwon' : '강원',
        'chungbuk' : '충북',
        'chungnam' : '충남',
        'jeonbuk'  :'전북',
        'jeonnam' : '전남',
        'gyeongbuk' : '경북',
        'gyuongnam' : '경남',
        'jeju' : '제주',
        'sejoung' : '세종'
    };
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
    LineChart,
    Selector,
    ButtonGroup,
    KoreaMap,
    StationList
  },
  data() {
    return {
      daily : [],
      dailyDict : {},
      today : {}, 
      btnstate : 0,
      selected : ['seoul'],
      currentRegion : '',
      mycity : 'seoul',
      eng2kor : eng2kor
    }
  },
  created() {
    com.daily()
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
    getStation(){
      if(this.selected.length > 1) {
        alert('지역을 하나만 선택해 주세요.');
        return;
      }
      if(this.selected.length == 0){
        alert('지역을 선택해주세요.');
        return;
      }
      this.currentRegion = this.selected[0];
      $('#stationModal').modal({
        show : true,
        backdrop : 'static'
      });
    }
    
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
