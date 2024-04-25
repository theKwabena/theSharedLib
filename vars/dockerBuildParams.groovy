import com.lutermart.config.DockerBuildParameters
import com.lutermart.config.DockerConfig

def call(Map params){
  return new DockerBuildParameters(params)
}