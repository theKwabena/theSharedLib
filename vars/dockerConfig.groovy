import com.lutermart.config.DockerConfig

def call(String registry, String credentials_id){
    return new DockerConfig(registry:registry, credentials_id:credentials_id)
}
