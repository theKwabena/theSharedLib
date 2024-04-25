import com.lutermart.Docker


def call(image, String tag='latest', String registry, String credentials_id) {
    return new Docker(this).pushDockerImage(image, tag, registry, credentials_id)
}