import com.lutermart.Docker


def call(Map params) {
    return new Docker(this).pushDockerImage(params)
}