<template>
    <div class="monthlySeoul">
        <select id="city" v-model="selected">
            <option v-for="(value, key) in options" v-bind:key="key">
                {{ value.value }}
            </option>
        </select>
        
        <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-secondary active" @click="loadDaily">
                <input type="radio" name="options" id="option1" autocomplete="off" checked> 시간별
            </label>
            <label class="btn btn-secondary" @click="loadWeekly">
                <input type="radio" name="options" id="option2" autocomplete="off" > 일별
            </label>
            <label class="btn btn-secondary" @click="loadMonthly">
                <input type="radio" name="options" id="option3" autocomplete="off" > 주별
            </label>
        </div>
        
        <div id="chart"></div>  
    </div>

</template>

<script>
    import { bb } from 'billboard.js'
    import $ from 'jquery'

    var options = [
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

    var columns_data = ["data1", 30, 200, 100, 400, 150, 250];

    export default {
        name: 'LineChart',
        mounted() {    
            this.drawChart();
        },
        data: function() {
            return {
                options,
                selected: "seoul",
                columns_data,
            }
        },
        methods: {
            loadDaily: function() {
                var col = ["시간별 평균 미세먼지" ,];
                var self = this                
                
                // load json file
                $.getJSON('hour.json', function(data) {
                    var i = 0;
                    for(i=0;i < data.length;i++) {
                        col.push(data[i][self.selected]);
                    }
                });
                this.columns_data = col;
                this.drawChart()
            },
            loadWeekly: function() {
                var col = ["일별 평균 미세먼지"];
                var dates = [];
                $.getJSON('daily.json', function(data) {
                    var i = 0;
                    for(i=0;i < 7;i++) {
                        col.push(data[i][self.selected]);
                        dates.push(data[i].date)
                    }
                });
                this.columns_data = col;
                this.drawChart();
            },
            loadMonthly: function() {
                var col = ["주별 평균 미세먼지"];
                $.getJSON('daily.json', function(data) {
                    var i = 0, j = 0, sum = 0;
                    for(i=0;i < data.length;i++) {
                        if (j%7==0) {
                            col.push(sum/7);
                            sum = 0;
                        } else {
                            j++;
                            sum += data[i][self.selected];
                        }
                    }
                });
                this.columns_data = col;
                this.drawChart();
            },
            drawChart: function() {
                bb.generate({
                    bindto: "#chart",
                    data: {
                        columns: [
                            this.columns_data
                        ]
                    }
                });
            },
             
        }       
    }
</script>

<style scoped>
</style>


