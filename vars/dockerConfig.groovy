import com.lutermart.DockerConfig

static def call(
        String registry, String credentials
){
    return new DockerConfig(Script:this, registry:registry, credentials:credentials)
}
