import com.lutermart.Docker


def call(image, String registry_url, String credentials_id, String tag){
    return new Docker(this).pushDockerImage(image, registry_url, credentials_id, tags)
}