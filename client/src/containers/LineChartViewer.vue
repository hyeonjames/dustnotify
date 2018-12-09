<template>
    <div>
        <selector id="vSelect" v-model="selectedCity" @input="onchange()" :value="selectedCity"/>
        <button-group id="btnGroup" v-model="btnstate" />
        <line-chart id="lineChart" :city="selectedCity" :state="btnstate" @input="onChange()"/>
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
    props: ['selectedCity', 'value'],
    data: function() {
        return {
            btnstate: 0
        }
    },
    methods: {
        onchange() {
            this.$emit('input', this.selectedCity);
        }
    },
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
