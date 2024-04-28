package com.lutermart

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
        def nginxTemplate = script.libraryResource 'com/lutermart/templates/nginx.template.conf'
        def binding = [
                siteName : siteName,
                rootDirectory : rootDirectory,
        ]

        def render = script.renderTemplate(nginxTemplate, binding)

        def nginxConfFile = script.writeFile(file: "nginx/${siteName}.conf", text: render)
        return nginxConfFile
    }

}

