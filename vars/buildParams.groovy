import com.lutermart.BuildParams
import com.lutermart.DockerConfig

static def call(
        String imageName,
        String imageTag,
        Boolean push,
        DockerConfig config
){
  return new BuildParams(
          Script:this,imageName:imageName, config: config, imageTag: imageTag, push:push
  )
}