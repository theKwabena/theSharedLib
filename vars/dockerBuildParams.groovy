import com.lutermart.config.DockerBuildParameters
import com.lutermart.config.DockerConfig

def call(
        String imageName,
        String imageTag = 'latest',
        Boolean push=false,
        DockerConfig config
){
  return new DockerBuildParameters(imageName:imageName, imageTag:imageTag, push:push, config:config)
}