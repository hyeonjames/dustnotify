<template>
    <div class="monthlySeoul">
        <h1>{{ msg }}</h1>
        <div id="chart"></div>        
    </div>

</template>


<script>
    import d3 from '@/assets/d3'
    import { bb } from 'billboard.js'
    
    export default {
        name: 'LineChart',
        props: {
            msg: String
        },
        mounted() {    
            var chart = bb.generate({
                bindto: "#chart",
                data: {
                    columns: [
                        ["data1", 30, 200, 100, 400, 150, 250],
                        ["data2", 50, 20, 10, 40, 15, 25]
                    ]
                }
            });
        },
        data() {
        }       
    }
</script>

<style scoped>
</style>


