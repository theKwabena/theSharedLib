import com.lutermart.config.DockerConfig

def call(String registry, String credentials_id){
    return new DockerConfig(registry, credentials_id)
}
