<template>
    <div>
        <selector id="vSelect" v-model="selectedCity"/>
        <button-group id="btnGroup" v-model="btnstate" />
        <line-chart id="lineChart" :city="selectedCity" :state="btnstate"/>
        <p>{{ btnstate }}</p>
    </div>
</template>


<script>
import Selector from '../components/Selector.vue';
import LineChart from '../components/LineChart.vue';
import ButtonGroup from '../components/ButtonGroup.vue';

export default {
    name: 'LineChartViewer',
    components: {
        Selector,
        LineChart,
        ButtonGroup
    },
    props: {
        dailyFile: {
            String,
            default: 'daily.json'
        },
        hourFile: {
            String,
            default: 'hour.json'
        },
        weekFile: {
            String, 
            default: 'week.json'
        },
    },
    data: function() {
        return {
            selectedCity: ["busan", "gwangju", "jeju"],
            btnstate: 0
        }
    }
}
</script>

<style scoped>
#vSelect {
    float: left;
    width: 50%;
    margin: 0 5px 0;
}
#btnGroup {
    float: right;
    margin: 5px 5px 5px;
}
#lineChart {
    margin: 10px 0 10px;
}
</style>
