import com.lutermart.DockerConfig

def call(String registry, String credentials){
    return new DockerConfig(registry, credentials)
}
