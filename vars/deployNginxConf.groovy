import com.lutermart.Flutter

def call(nginxConf, server){
    return new Flutter(this).deployNginxConf(nginxConf, server)
}