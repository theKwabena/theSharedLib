import com.lutermart.config.NginxWebApp

static def call(Map params){
    return new NginxWebApp(params)
}