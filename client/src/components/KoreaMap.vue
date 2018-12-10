<template>
    <div>
        <h6>실시간 미세먼지 정보<br/> {{dust.date}}</h6>
        <svg v-bind:width="svg.width" v-bind:height="svg.height"/>
        <div id="crd" class="region-card" v-show="card.visible">
            <h6>{{card.title}}</h6>
            <span>{{card.content}}</span>
        </div>
    </div>
</template>

<script>
import * as topojson from 'topojson'
import * as d3 from 'd3'
import $ from 'jquery'
import com from '../com.js'
export default {
    name : 'koreamap',
    props : ['dust', 'value'],
    data(){
        return {
            svg : {
                width : 530,
                height : 500
            },
            curRegion : null,
            regionCodeDict : {},
            card : { visible : false }
        }
    },
    created() {
        com.get('api/region/list').then((r)=>{
            for(var index in r) {
                let c = r[index];
                this.regionCodeDict[c.regionCode] = c;
            }
            this.paint();
        })
    },
    watch :{
        'value'(){
            this.paint();
        },
        'dust'(){
            this.paint();
        },
        'curRegion' (){
            this.paint();
            var reg = this.regionCodeDict[this.curRegion];
            if(!reg) {
                return;
            }
            var val = this.dust[reg.regionEngName];
            if(val == undefined || val == null) {
                return;
            }
            this.card.title = reg.regionKorName;            
            this.card.content = `미세먼지 지수 ${val} ( ${this.getIndex(val).alias} )`
            
            this.$forceUpdate();
        }
    },
    mounted() {
            
        var svg = d3.select("svg")
        var projection = d3.geoMercator()
            //.center([126.9895, 37.5651])
            .center([126, 37.8])
            .scale(4000)
            .translate([120,90]);
        var map = svg.append("g").attr("id", "map")
        var path = d3.geoPath().projection(projection);
        var self = this;
        $('svg').on('mouseout', (e)=>{
            if($(e.target).is('svg')) {
                self.card.visible = false;
                self.curRegion = null;
            }
        })
        $.getJSON('topo.json', (js)=> {
            var nation = topojson.feature(js, js.objects.skorea_provinces_geo).features
            map.selectAll("path")
                .data(nation)
                .enter().append("path")
                .attr('fill', 'lightgreen')
                .attr('stroke','lightgray')
                .attr('stroke-width',0.5)
                .attr("class", (d)=> { 
                    return "municipality c" + d.properties.code;
                })
                .attr("d", path)
                .on('click' ,function (e){
                    console.log(e);
                    if(e.properties) {
                        self.clicked(e.properties.name_eng);
                    }
                })
                .on('mouseover', function (e){
                    if(e.properties) {
                        self.curRegion = e.properties.code;
                        
                    }
                    self.card.visible = true;
                })
                .on('mousemove', function (){
                    $('#crd').css('left', d3.event.pageX+20).css('top', d3.event.pageY+20);

                });
                this.paint();
            
        });
    },
    methods : {
        // clicked(e) {
        //     let index = this.value.indexOf(e);
        //     if(index >= 0) {
        //         this.value.splice(index,1);
        //     } else{
        //         this.value.push(e);
        //     }
        //     this.$emit('input', this.value);
        // },
        clicked(e) {
            let newValue = [];
            let append = true;
            for(var i=0;i<this.value.length;i++){
                if(this.value[i] != e) {
                    newValue.push(this.value[i]);
                } else {
                    append = false;
                }
            }
            if(append) {
                newValue.push(e);
            }
            this.$emit('input', newValue);
        },
        getIndex( val) {
            const r = [
                {min: 0, max: 15, color : 'blue', alias : '양호'},
                {min: 16, max: 35, color : 'green', alias : '보통'},
                {min:36, max:75, color : 'yellow', alias : '나쁨'},
                {min:76, max:500, color : 'red', alias : '매우나쁨'}
            ]
            for(var i=0;i<r.length;i++) {
                if(val >= r[i].min && val <= r[i].max) {
                    return r[i];
                }
            }
            return r[r.length-1];
        },
        getColor (e) {
            if(!e.properties) {
                return 'lightgreen';
            }
            if(this.value.indexOf(e.properties.name_eng) >= 0) {
                return 'gray';
            }
            if(e.properties.code == this.curRegion) {
                return 'lightgray';
            }
            var region = this.regionCodeDict [e.properties.code];
            if(!region) {
                return 'lightgreen';
            }
            if(!this.dust) {
                return 'lightgreen'
            }
            var o = this.dust[region.regionEngName];
            if(o == undefined || o == null) {
                return 'lightgreen';
            }
            return this.getIndex(o).color;
        },
        paint() {
            d3.selectAll('path')
            .attr('fill',  (e)=> {
                return this.getColor(e);
            })
        }
    }
}
</script>

<style scoped>
    svg {
        background-color: #cbeafc;
    }
    .region-card {
        position:absolute;
        background-color:rgb(163, 185, 119);
        border: 1px solid rgb(185, 185, 185);
        padding : 10px;
    }
    svg .province {
        stroke: #fff
    }
</style>
