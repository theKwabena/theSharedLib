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
                siteName : siteName,
                rootDirectory : rootDirectory,
        ]

        def render = Helpers.renderTemplate(binding, nginx_template)

        def nginxConfFile = script.writeFile(file: "nginx/${siteName}.conf", text: render)
        return nginxConfFile
    }

}

