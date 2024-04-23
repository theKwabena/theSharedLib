import com.lutermart.BuildParams
import com.lutermart.Docker


def call(String imageName, String imageTag, Boolean push){
    // Takes the imageName, image tag and a boolean push which specifies whether to push
    // the built docker image to a registry
    return new Docker(this).buildDockerImage(BuildParams params)
}