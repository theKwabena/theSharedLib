package com.lutermart

import com.lutermart.config.Helpers

class Flutter implements Serializable{
    def script


    Flutter(script) {
        this.script =script
    }

    def flutter_doctor(){
        script.echo "----- RUNNING FLUTTER DOCTOR -----"
        script.sh "flutter doctor"

    }

    def test_app(coverage=false, directory=null){
        script.echo "---- TESTING APP -----"
        script.sh "flutter test"
    }

    def buildApp(){
        script.sh "flutter build web --output-dir=build"
    }

//    def generateNginxConf(siteName, rootDirectory){
//        String nginx_template = script.libraryResource 'com/lutermart/templates/nginx.template.conf'
//        def binding = [
//                site_name : siteName,
//                 root_directory: rootDirectory,
//        ]
//        def render = Helpers.renderTemplate(binding, nginx_template)
//
//        script.writeFile(file: "${siteName}.conf", text: render)
//        return "$siteName"
//    }

    def deployNginxConf(server) {
        // Check if the configuration file already exists on the server
        script.sshagent([server.credentials_id]) {
            def existingConfFile = script.sh(
                    script: "ssh $server.user@$server.address '[ -e /home/swarm/nginx-conf.d/${nginxConfFile} ] && echo true || echo false'", returnStdout: true).trim()

            script.echo "$existingConfFile"

            // Read the content of the new configuration file
            def newConfContent = script.readFile(nginxConfFile)

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
    }

    def deployBuildFiles(appName, buildDir, server) {
        // Check if the build directory already exists on the server
        script.sshagent([server.credentials_id]) {
            def existingBuildDinr = script.sh(script: "ssh $server.user@$server.address '[ -d /home/swarm/sites_available/${appName} ] " +
                    "&& echo exists || echo not_exists'", returnStdout: true).trim()
            // Check for changes in the build files
            def diffOutput = ""
            if (existingBuildDir == 'exists') {
                diffOutput = script.sh(script: "rsync -avnc --delete ${buildDir}/ $server.user@$server.address:/home/swarm/sites_available/${appName}/", returnStdout: true).trim()
            }

            if (existingBuildDir == 'not_exists' || diffOutput) {
                // Move the new build files to the server
                script.sh "rsync -avz --delete ${buildDir}/ $server.user@$server.address:/home/swarm/sites_available/${appName}/"
            } else {
                script.echo "No changes detected in build files. Skipping deployment."
            }
        }
    }

}

