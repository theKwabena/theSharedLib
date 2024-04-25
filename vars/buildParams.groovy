import com.lutermart.BuildParams
import com.lutermart.DockerConfig

def call(
        String imageName,
        String imageTag,
        Boolean push,
        DockerConfig config
){
  return new BuildParams(imageName,config,imageTag,push)
}