
import $ from 'jquery'
import q from 'q'
import './dateformat.js'
let host = ''
let crossDomain = false
let xhrFields = null
const cols = ["seoul", "busan", "daegu", "incheon", "gwangju", "daejeon", "ulsan", "sejong", "gyeonggi", "gangwon", "chungbuk", "chungnam", "jeonbuk", "jeonnam", "gyeongbuk", "gyeongnam", "jeju"];
let daily = null;
let hour =null;
let com = {
    weekData(month) {
        var prevWeek = -1;
        var r = [];
        
        for(var i=0;i<month.length;i++) {
            var date = new Date(month[i].date)
            var week = date.getWeekOfYear();
            if(week != prevWeek) {
                r.push({
                    from : date.format('yyyy-mm-dd'),
                    week : date.getWeekOfMonth(),
                    month : date.getMonth()+1,
                    count : 0
                });
            }
            var f = r[r.length-1];
            f.count = f.count+1;
            f.to = date.format('yyyy-mm-dd')
            for(var j=0;j<cols.length;j++) {
                f[cols[j]] = (f[cols[j]] || 0) + month[i][cols[j]];
            }
            prevWeek = week;
        }
        for(var i=0;i<r.length;i++) {
            for(var j=0;j<cols.length;j++) {
                r[i][cols[j]] = r[i][cols[j]] / r[i].count;
            }
        }
        return r;
    },
    daily (update = false) {
        let p = q.defer();
        if(!update && daily) {
            p.resolve(daily);
        } else {
            com.get('api/stat/get/daily')
            .then((r)=>{
                daily = r;
                p.resolve(r);
            }, p.reject);

        }
        return p.promise;
    },
    hour (update = false) {
        let p = q.defer();
        if(!update && hour) {
            p.resolve(hour);
        } else {
            com.get('api/stat/get/hour')
            .then((r)=>{
                hour = r;
                p.resolve(r);
            }, p.reject);

        }
        return p.promise;

    },

    get (url) {
        let p = q.defer();
        $.ajax({
            method : 'GET',
            url : host + url,
            crossDomain: crossDomain,
            xhrFields: xhrFields,
            dataType : 'json'
        }).done((data)=>{
            p.resolve(data);
        })
        return p.promise;
    },
    get2 (url) {
        let p = q.defer();
        $.ajax({
            method : 'GET',
            url : host + url,
            crossDomain: crossDomain,
            xhrFields: xhrFields,
            dataType : 'json'
        }).done((data)=>{
            p.resolve(data);
        })
        return p.promise;
    },
    post (url, data) {
        let p = q.defer();
        $.ajax({
            method : 'POST',
            url : host + url,
            crossDomain: crossDomain,
            xhrFields: xhrFields,
            data : data,
            dataType : 'json'
        }).done((data)=>{
            p.resolve(data);
        })
        return p.promise;
    }
}

window.com = com;
export default com;