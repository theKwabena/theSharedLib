import com.lutermart.Docker


def call(String image, String registry, String credentials_id) {
    return new Docker(this).pushDockerImage(image, registry, credentials_id)
}