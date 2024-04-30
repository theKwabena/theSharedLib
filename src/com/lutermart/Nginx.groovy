package com.lutermart

import com.lutermart.config.NginxHost
import com.lutermart.config.NginxWebApp


class Nginx implements Serializable{

    def script;

    Nginx(script){
        this.script = script
    }

    def deployApp(NginxHost host, NginxWebApp app) {

        script.sshagent([host.server.credentialsID]) {

            script.echo "Checking if the build directory exists"
            def existingBuildDir = script.sh(
                    script: "ssh $host.server.user@$host.server.address '[ -d ${host.sitesDirectory}/${app.appName} ] " +
                    "&& echo exists || echo not_exists'", returnStdout: true).trim()

            // Check for changes in the build files
            def diffOutput = ""
            if (existingBuildDir == 'exists') {
                script.echo "Build directory exists, checking file changes"
                diffOutput = script.sh(script:
                        "rsync -avnc --delete ${app.buildDirectory} $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/",
                        returnStdout: true).trim()
            }

            script.echo "$diffOutput for the output of files"

            if (existingBuildDir == 'not_exists' || diffOutput) {
                // Move the new build files to the server
                script.sh "rsync -avz --delete ${app.buildDirectory}/ $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/"
            }

            
        }
    }
}
