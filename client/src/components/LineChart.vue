<template>
    <div class="line-chart-viewer">
        <!-- <selector v-bind:selected="city"/> -->

        <h4> {{ city +'의 '+ msg }} </h4>

        <select id="city" v-model="city" @change="onChange()">
            <option v-for="item in options" v-bind:key="item.key" v-bind:value="item.value">
                {{ item.value }}
            </option>
        </select>

        <div id="btngroup" class="btn-group btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-primary active" @click="loadDaily">
                <input type="radio" name="options" id="option1" autocomplete="off" checked> 시간별
            </label>
            <label class="btn btn-primary" @click="loadWeekly">
                <input type="radio" name="options" id="option2" autocomplete="off" > 일별
            </label>
            <label class="btn btn-primary" @click="loadMonthly">
                <input type="radio" name="options" id="option3" autocomplete="off" > 주별
            </label>
        </div>

        <div id="chart"></div>  
    </div>

</template>

<script>
    // import Selector from './components/Selector.vue'

    import { bb } from 'billboard.js'
    import $ from 'jquery'

    var columns_data = ["data1", 30, 200, 100, 400, 150, 250];
    
    export default {
        name: 'LineChart',
        props: {
            daily: {
                String,
                default: 'daily.json' 
            },
            hour: {
                String,
                default: 'hour.json'
            },
            city: {
                String, 
                default: 'seoul'
            },
            options: {
                Array,
                default: function() {
                    return [
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
                                { text:"세종", value: "sejong"}        
                            ];
                }        
            },
            
        },
        mounted() {    
            this.loadDaily();
        },
        data: function() {
            return {
                columns_data,
                state: 0,
                msg: {
                    String,
                    default: "ㅁㅁ"
                } 
            }
        },
        methods: {
            loadDaily: function() {
                this.msg = "시간별 평균 미세먼지";
                var col = ["시간별 평균 미세먼지" ];
                var time = ["times"]; 
                this.state = 0;             
                // load json file
                $.getJSON(this.hour, (data)=> {
                    var i = 0;
                    for(i=0;i < data.length;i++) {
                        col.push(data[i][this.city]);
                        time.push(new Date(data[i].date));
                    }
                    this.columns_data = col;
                    
                    bb.generate({
                        bindto: "#chart",
                        data: {
                            x: 'times',
                            columns: [
                                time,
                                this.columns_data
                            ]
                        },
                        axis: {
                            x: {
                                type: "timeseries",
                                tick: {
                                    format: "%m-%d %H:%M"
                                }
                            }
                        }
                    })
                });
            },
            loadWeekly: function() {
                this.msg = "일주일간 일별 평균 미세먼지"
                var col = ["일별 평균 미세먼지"];
                var dates = ["dates"];
                this.state = 1;
                $.getJSON(this.daily, (data) =>{
                    var i = 0;
                    for(i=0;i < 7;i++) {
                        col.push(data[i][this.city]);
                        dates.push(new Date(data[i].date));
                    }
                    this.columns_data = col;
                    
                    bb.generate({
                        bindto: "#chart",
                        data: {
                            x: 'dates',
                            columns: [
                                dates,
                                this.columns_data
                            ]
                        },
                        axis: {
                            x: {
                                type: "timeseries",
                                tick: {
                                    format: "%Y-%m-%d"
                                }
                            }
                        }
                    })
                });
            },
            loadMonthly: function() {
                this.msg = "한달간 주별 평균 미세먼지"
                var col = ["주별 평균 미세먼지"];
                this.state = 2;
                $.getJSON(this.daily, (data)=> {
                    var i = 0, j = 0, sum = 0;
                    for(i=0;i < data.length;i++) {
                        if (j%7==0) {
                            col.push(sum/7);
                            sum = 0;
                        } else {
                            j++;
                            sum += data[i][this.city];
                        }
                    }
                    this.columns_data = col;
                    this.drawChart();
                });
                
            },
            drawChart: function() {
                bb.generate({
                    bindto: "#chart",
                    data: {
                        columns: [
                            this.columns_data
                        ],
                        type: "line"
                    },
                });
            },
            onChange: function() {
                switch (this.state) {
                    case 0:
                        this.loadDaily();
                        break;
                    case 1:
                        this.loadWeekly();
                        break;
                    case 2:
                        this.loadMonthly();
                        break;
                    default:
                        break;
                }
            }
             
        }       
    }
</script>

<style scoped>
h4 {
    margin: 10px 0 10px;
}
#btngroup {
    float: right;
    margin: 0 5px 0
}
#chart {
    margin: 7px 7px 7px
}
</style>


