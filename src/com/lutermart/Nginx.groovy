package com.lutermart

import com.lutermart.config.NginxHost
import com.lutermart.config.NginxWebApp


class Nginx implements Serializable{

    def script;

    Nginx(script){
        this.script = script
    }

    def deployConfigFile(server, configFile){
        def existingConfFile = script.sh(
                script: "ssh $server.user@$server.address '[ -e /home/swarm/nginx-conf.d/${configFile} ] && echo true || echo false'", returnStdout: true).trim()

        script.echo "$existingConfFile"

        // Read the content of the new configuration file
        def newConfContent = script.readFile(configFile)

        // Compare with the existing configuration file if it exists
        if (existingConfFile == 'exists') {
            def existingConfContent = script.sh(
                    script: "'cat /home/swarm/nginx-conf.d/${nginxConfFile}'", returnStdout: true
            ).trim()

            if (existingConfContent == newConfContent) {
                script.echo "No changes detected in Nginx configuration. Skipping deployment."
                return
            }
        }

        // Move the new configuration file to the server
        script.sh "scp ${nginxConfFile} ${server.user}@${server.address}:/home/swarm/nginx-conf.d/"
    }

    def deployApp(NginxHost host, NginxWebApp app) {

        script.sshagent([host.server.credentialsID]) {

//            script.echo "Checking if the build directory exists"
//            def existingBuildDir = script.sh(
//                    script: "ssh $host.server.user@$host.server.address '[ -d ${host.sitesDirectory}/${app.appName} ] " +
//                    "&& echo exists || echo not_exists'", returnStdout: true).trim()
//
//            // Check for changes in the build files
//            def diffOutput = ""
//            if (existingBuildDir == 'exists') {
//                script.echo "Build directory exists, checking file changes"
//                diffOutput = script.sh(script:
//                        "rsync -avnc --delete ${app.buildDirectory} $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/",
//                        returnStdout: true).trim()
//            }
//
//            script.echo "$diffOutput for the output of files"
//
//            if (existingBuildDir == 'not_exists' || diffOutput) {
//                // Move the new build files to the server
//                script.sh "rsync -avz --delete ${app.buildDirectory}/ $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/"
//            }

            // Skip all checks and sync build directory with server
            script.echo "Moving build files to nginx sites directory"
            script.sh "rsync -avz --delete ${app.buildDirectory}/ $host.server.user@$host.server.address:$host.sitesDirectory/${app.appName}/"


            // Deploy app config file to nginx
            def existingConfFile = script.sh(
                    script: "ssh $host.server.user@$host.server.address" +
                            " '[ -e $host.configurationDirectory/${app.appName}.conf ] " +
                            "&& echo true || echo false'", returnStdout: true
            ).trim();

            def newConfContent = script.readFile(app.configFile)

            if (existingConfFile == 'exists') {
                script.echo "Config file already exists, Checking for changes"
                def existingConfContent = script.sh(
                        script: "'cat $host.configurationDirectory/$app.configFile'", returnStdout: true
                ).trim()

                if (existingConfContent == newConfContent) {
                    script.echo "No changes detected in Nginx configuration."
                    return
                }
            }

            script.echo "Moving app config file to nginx sites configuration directory"
            script.sh "scp $app.configFile $host.server.user@$host.server.address:$host.configurationDirectory/$app.appName"

        }
    }
}
