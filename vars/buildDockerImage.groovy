import com.lutermart.Docker
import com.lutermart.config.DockerBuildParameters

def call(DockerBuildParameters params){
    // Takes the imageName, image tag and a boolean push which specifies whether to push
    // the built docker image to a registry
    return new Docker(this).buildDockerImage(params)
}