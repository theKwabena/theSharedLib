package com.lutermart

class Flutter implements Serializable{
    def script


    Flutter(script) {
        this.script =script
    }

    def flutter_doctor(){
        script.echo "----- RUNNING FLUTTER DOCTOR -----"
        script.sh "flutter"

    }

    def test_app(coverage=false, directory=null){
        script.echo "---- TESTING APP -----"
        script.sh "flutter test"
    }


}

