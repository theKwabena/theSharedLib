import com.lutermart.Docker


def call(String credentialsId, def repository=null){
    return new Docker(this).dockerLogin(credentialsId, repository)
}