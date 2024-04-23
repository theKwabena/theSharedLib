import com.lutermart.Docker


def call(String imageName){
    return new Docker(this).pushDockerImage(imageName)
}