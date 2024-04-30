import com.lutermart.config.NginxHost

static def call(Map params){
    return new NginxHost(params)
}