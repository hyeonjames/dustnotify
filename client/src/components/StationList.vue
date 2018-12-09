<template>
    <div class="station-list">
        <table class="table">
            <tr>
                <th>이름</th>
                <th>주소</th>
            </tr>
            <tr v-bind:key="item.stationName" v-for="item in list">
                <td>{{item.stationName}}</td>
                <td><a v-bind:href="`http://map.daum.net/?q=${item.addr}`">{{item.addr}}</a></td>
            </tr>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item" v-bind:class="{disabled : curPage == 1}">
                    <span class="page-link" href="#" @click="go(curPage-1)">&lt;&lt;</span>
                </li>
                <li class="page-item" v-for="pg in page" v-bind:key="pg" v-bind:class="{active : pg == curPage}">
                    <span class="page-link" href="#" @click="go(pg)">{{pg}}</span>
                </li>
                <li class="page-item" v-bind:class="{disabled : curPage == page.length}">
                    <span class="page-link" href="#" @click="go(curPage+1)">&gt;&gt;</span>
                </li>
            </ul>
        </nav>
    </div>

</template>

<script>
const countPerPage = 10;
import com from '../com.js'
export default {
    name : 'stationlist',
    props : ['region'],
    data() {
        return {
            all : [],
            list : [],
            page : [],
            curPage : 1
        }
    },
    created(){
        this.update();
    },
    watch : {
        'region'(){
            this.update();
        }
    },
    methods : {
        go(n){
            this.curPage = n;
            this.list = this.all.slice((n-1)*countPerPage, n*countPerPage);
        },
        update(){
            if(this.region){
                com.get(`api/region/station/${this.region}`)
                .then((r)=>{
                    this.all = r;
                    var mx = Math.floor((r.length-1) / countPerPage) + 1;
                    this.page = [];
                    for(var i=1;i<=mx;i++) {
                        this.page.push(i);
                    }
                    this.go(1);
                });
            }
        }
    }
}
</script>

<style>
    .station-list {
        width:500px;
    }
</style>
