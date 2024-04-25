import com.lutermart.config.DockerBuildParameters
import com.lutermart.config.DockerConfig

def call(
        String imageName,
        String imageTag,
        Boolean push,
        DockerConfig config
){
  return new DockerBuildParameters(imageName, config, imageTag,push)
}