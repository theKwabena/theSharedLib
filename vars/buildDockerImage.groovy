import com.lutermart.Docker

def call(String imageTag){
    return new Docker(this).buildDockerImage(imageTag)
}