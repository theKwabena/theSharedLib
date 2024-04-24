import com.lutermart.Docker

static def call(
        String registry, String credentials
){
    return new DockerConfig(registry, credentials)
}
