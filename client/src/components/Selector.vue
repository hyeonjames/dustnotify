<template>
    <v-select multiple v-model="selected" :options="arrayOfObjects" :on-change="updateSelected">
    </v-select>
</template>

<script>
import vSelect from 'vue-select'
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
    'sejong' : '세종'
};

export default {
    name: 'Selector',
    components: {
        vSelect
    },
    props: {
        value: null       
    },
    data: function() {
        return {
            arrayOfObjects: [
                { label:"서울", value: "seoul" },
                { label:"부산", value: "busan" },
                { label:"대구", value: "daegu" },
                { label:"인천", value: "incheon" },
                { label:"광주", value: "gwangju" },
                { label:"대전", value: "daejeon" },
                { label:"울산", value: "ulsan" },
                { label:"경기", value: "gyeonggi" },
                { label:"강원", value: "gangwon"  },
                { label:"충북", value: "chungbuk"},
                { label:"충남", value: "chungnam"},
                { label:"전북", value: "jeonbuk" },
                { label:"전남", value: "jeonnam" },
                { label:"경북", value: "gyeongbuk" },
                { label:"경남", value: "gueongnam" },
                { label:"제주", value: "jeju" },
                { label:"세종", value: "sejong"}    
            ],
            selected: []
        }   
    },
    watch : {
        'value' (from,to) {
            if(JSON.stringify(from) != JSON.stringify(to))
                this.updateValue();
        }
    },
    created: function() {
        // $.getJSON('daily.json', (data)=> {
        // // $.getJSON(this.jsonFile, (data)=> {
            // this.arrayOfObjects = Object.keys(data[0]);
            // this.arrayOfObjects.shift();
        // });
        this.updateValue();
    },
    methods: {
        updateValue() {
            var city = [];
            for (var i = 0; i < this.value.length; i++) {
                var cityObject = {
                    label : regionDict[this.value[i]],
                    value : this.value[i]};
                city.push(cityObject);
            }
            this.selected = city;
        },
        updateSelected() {
            var city = [];
            for (var i = 0; i < this.selected.length; i++) {
                city.push(this.selected[i].value);
            }
            this.$emit('input', city);
        }
    },
}
</script>

<style scoped>

</style>
