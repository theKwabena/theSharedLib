package com.lutermart

import com.lutermart.config.NginxHost
import com.lutermart.config.NginxWebApp


class Nginx implements Serializable{

    def script;

    Nginx(script){
        this.script = script
    }

    def deployApp(NginxHost host, NginxWebApp app) {
        // Check if the build directory already exists on the server
        script.sshagent([host.server.credentialsID]) {
            def existingBuildDir = script.sh(
                    script: "ssh $host.server.user@$host.server.address '[ -d ${host.sitesDirectory}/${app.appName} ] " +
                    "&& echo exists || echo not_exists'", returnStdout: true).trim()

            // Check for changes in the build files
            def diffOutput = ""
            if (existingBuildDir == 'exists') {
                diffOutput = script.sh(script:
                        "rsync -avnc --delete ${app.buildDirectory} $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/",
                        returnStdout: true).trim()
            }

            if (existingBuildDir == 'not_exists' || diffOutput) {
                // Move the new build files to the server
                script.sh "rsync -avz --delete ${app.buildDirectory}/ $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/"
            } else {
                script.echo "No changes detected in build files. Skipping deployment."
            }
        }
    }
}
