import com.lutermart.config.BuildParams
import com.lutermart.Docker




def call(BuildParams params ){
    // Takes the imageName, image tag and a boolean push which specifies whether to push
    // the built docker image to a registry
    return new Docker(this).buildDockerImage(params)
}