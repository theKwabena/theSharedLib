import com.lutermart.config.Server

static def call(Map params){
    return new Server(params)
}