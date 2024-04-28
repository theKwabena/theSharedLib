import com.lutermart.Flutter

def call(siteName, rootDirectory){
    return new Flutter(this).generateNginxConf(siteName, rootDirectory)
}