import com.lutermart.Flutter

def call(appName, buildDir, server){
    return new Flutter(this).deployBuildFiles(appName, buildDir, server)
}