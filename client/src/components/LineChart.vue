<template>
    <div class="line-chart-viewer">
        <h3> {{ msg }} </h3>
        <div id="chart"></div>  
    </div>

</template>

<script>
    import { bb } from 'billboard.js'
    import $ from 'jquery'
    import com from '../com.js'

    const regionDict = {
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

    export default {
        name: 'LineChart',
        props: {
            city: {
                Array,
                default: function() {
                    return Array();
                } 
            },
            state: {
                Number,
                default: 0
            },
        },
        data: function() {
            return {
                msg: {
                    String,
                    default: "ㅁㅁ"
                },

            }
        },
        mounted() {
            this.loadDaily();
        },
        methods: {
            loadDaily: function() {
                this.state = 0; 
                this.msg = "시간별 평균 미세먼지";

                var col = [];
                var time = ["times"];
                var columns_data = []; 
                
                for(var i=0; i<this.city.length; i++) {
                    col.push(Array(regionDict[this.city[i]]));
                }

                // load json file

                com.hour()
                    .then((data)=> {
                        for (var i=0; i < data.length; i++) {
                            time.push(new Date(data[i].date));
                        for (var j=0; j < this.city.length; j++) {
                            var cityName = this.city[j];
                            col[j].push(data[i][cityName]);
                        }   
                    }
                    columns_data = Array(time);
                    columns_data = columns_data.concat(col);
                                        
                    bb.generate({
                        bindto: "#chart",
                        data: {
                            x: 'times',
                            columns: columns_data
                        },
                        axis: {
                            x: {
                                type: "timeseries",
                                tick: {
                                    format: "%m-%d %H:%M"
                                }
                            }
                        },
                        zoom: {
                            enabled: true
                        }
                    });
                    });
                
                
            },
            loadWeekly: function() {
                this.msg = "일주일간 일별 평균 미세먼지"
                this.state = 1;
                var col = [];
                var dates = ["dates"];
                var columns_data = [];

                for(var i=0; i<this.city.length; i++) {
                    col.push(Array(regionDict[this.city[i]]));
                }
                
                com.daily()
                .then( (data) =>{
                    for(var i=0;i < 7;i++) {
                        dates.push(new Date(data[i].date));
                        for (var j=0; j < this.city.length; j++) {
                            var cityName = this.city[j];
                            col[j].push(data[i][cityName]);
                        }                        
                    }
                    columns_data = Array(dates);
                    columns_data = columns_data.concat(col);

                    bb.generate({
                        bindto: "#chart",
                        data: {
                            x: 'dates',
                            columns: columns_data
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
                this.state = 2;
                this.msg = "한달간 주별 평균 미세먼지"
                var col = [];
                var columns_data = [];

                for(var i=0; i<this.city.length; i++) {
                    col.push(regionDict[this.city[i]]);
                }
                
                com.weekData().then ((data)=> {
                    data = com.weekData(data);
                    for(var i=0; i < data.length; i++) {
                        for (var j=0; j < this.city.length; j++) {
                            var cityName = this.city[j];
                            col[j].push(data[i][cityName]);
                        }
                    }

                    columns_data = columns_data.concat(col);
                    bb.generate({
                        bindto: "#chart",
                        data: {
                            columns: columns_data
                        }, type: "line"
                    })
                });
            },
            updateChart: function() {
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

        },
        watch : {
            'state'() {
                this.updateChart();
            },
            'city'() {
                this.updateChart();
            }
        }    
    }
</script>

<style scoped>
h3 {
    margin: 10px;
    padding: 5px;
    font-size: x-large;
}
#chart {
    margin: 7px;
}
</style>
