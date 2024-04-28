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
        def nginxTemplate = libraryResource 'templates/nginx.conf.template'
        def binding = [
                siteName : siteName,
                rootDirectory : rootDirectory,

        ]

        def render = renderTemplate(nginxTemplate, binding)
        def nginxConf = nginxTemplate.replaceAll('\\{\\{ site_name \\}\\}', siteName)
                .replaceAll('\\{\\{ root_directory \\}\\}', rootDirectory)
        def nginxConfFile = writeFile(file: "nginx/${siteName}.conf", text: nginxConf)

        return nginxConfFile
    }

}

