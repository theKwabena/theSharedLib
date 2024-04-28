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

    def generateNginxConf(siteName, rootDirectory){
        String nginx_template = script.libraryResource 'com/lutermart/templates/nginx.template.conf'
        def binding = [
                site_name : siteName,
                 root_directory: rootDirectory,
        ]
        def render = Helpers.renderTemplate(binding, nginx_template)

        script.writeFile(file: "${siteName}.conf", text: render)
        return "$siteName"
    }

    def deployNginxConf(nginxConfFile, server) {
        // Check if the configuration file already exists on the server
        script.sshagent([server.credentials_id]) {
            def existingConfFile = script.sh(
                    script: "test /etc/nginx/conf.d/${nginxConfFile} && echo exists || echo not_exists", returnStdout: true
            ).trim()
            script.echo "$existingConfFile"

            // Read the content of the new configuration file
            def newConfContent = script.readFile(nginxConfFile)

            // Compare with the existing configuration file if it exists
            if (existingConfFile == 'exists') {
                def existingConfContent = script.sh(
                        script: "'cat /etc/nginx/conf.d/${nginxConfFile}'", returnStdout: true
                ).trim()

                if (existingConfContent == newConfContent) {
                    script.echo "No changes detected in Nginx configuration. Skipping deployment."
                    return
                }
            }

            // Move the new configuration file to the server
            script.sh "scp ${nginxConfFile} ${server.user}@${server.address}:/etc/nginx/conf.d/${nginxConfFile}.conf"
        }
    }

    def deployBuildFiles(appName, buildDir, serverAddress) {
        // Check if the build directory already exists on the server
        def existingBuildDir = sh(script: "ssh user@${serverAddress} '[ -d /var/www/${appName} ] " +
                "&& echo exists || echo not_exists'", returnStdout: true).trim()

        // Check for changes in the build files
        def diffOutput = ""
        if (existingBuildDir == 'exists') {
            diffOutput = sh(script: "rsync -avnc --delete ${buildDir}/ user@${serverAddress}:/var/www/${appName}/", returnStdout: true).trim()
        }

        if (existingBuildDir == 'not_exists' || diffOutput) {
            // Move the new build files to the server
            sh "rsync -avz --delete ${buildDir}/ user@${serverAddress}:/var/www/${appName}/"
        } else {
            echo "No changes detected in build files. Skipping deployment."
        }
    }

}

