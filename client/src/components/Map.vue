<template>
    <div id="chart"></div>
</template>

<script>
import d3 from '@/assets/d3'
import topojson from "topojson"
import provinces from "../assets/provinces/provinces-topo-simple.json"

export default {
    name: 'Map',
    data() {
        return {
            width: 800,
            height: 700
        }
    },
    mounted() {
        this.addmap()
    },
    methods: {
        addmap: function() {
            var svg = d3.select("#chart").append("svg")
                    .attr("width", this.width)
                    .attr("height", this.height);
            var map = svg.append("g").attr("id","map");
            var projection = d3.geoMercator()
                    .center([128, 36])
                    .scale(4000)
                    .translate([this.width/3, this.height/2]);
            var path = d3.geoPath().projection(projection);
            // add map
            d3.json(provinces, function(error, data) {
                var features = topojson.feature(data, data.objects['provinces-geo']).features;
                map.selectAll('path')
                    .data(features)
                    .enter().append('path')
                    .attr('class', function(d) { return 'province c' + d.properties.code })
                    .attr('d', path);
            });
        }
    }
}
</script>

<style scoped>
    svg {
            background-color: #a8daf7
    }
    svg text {
        color: #333;
        font-size: 10px;
        pointer-events: none;
        text-anchor: middle;
    }
    svg .province {
        fill: #efefef;
        stroke: #fff;
    }

</style>
