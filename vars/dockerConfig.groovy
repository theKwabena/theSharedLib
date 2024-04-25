import com.lutermart.config.DockerConfig

def call(Map params){
    return new DockerConfig(params)
}
