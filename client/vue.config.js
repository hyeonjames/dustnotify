/* es-lint-disable */
module.exports = {
    devServer:{
        proxy : {
            "/api":{
                target : 'http://localhost/'
            }
        }
    }
}