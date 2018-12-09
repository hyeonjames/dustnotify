<template>
    <div>
        <selector id="vSelect" v-model="selected"/>
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
    width: 60%;
    margin: 5px;
}
#btnGroup {
    float: right;
    margin: 5px;
    padding: 1px;
    height: 48px;
    width: 30%;
}
#lineChart {
    margin: 10px;
    clear: both;
}
</style>
