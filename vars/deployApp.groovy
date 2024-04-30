import com.lutermart.Nginx
import com.lutermart.config.NginxHost
import com.lutermart.config.NginxWebApp

def call(NginxHost host, NginxWebApp app) {
    return new Nginx(this).deployApp(host, app)
}