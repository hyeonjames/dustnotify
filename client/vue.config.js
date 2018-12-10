/* es-lint-disable */
module.exports = {
    devServer:{
        proxy : {
            "/api":{
                target : 'http://ec2-13-209-117-20.ap-northeast-2.compute.amazonaws.com/'
            }
        },
        port : 8000
    }
}