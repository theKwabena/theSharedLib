import com.lutermart.config.BuildParams
import com.lutermart.config.DockerConfig

def call(
        String imageName,
        String imageTag,
        Boolean push,
        DockerConfig config
){
  return new BuildParams(imageName,config,imageTag,push)
}