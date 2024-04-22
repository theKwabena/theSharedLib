package com.lutermart

class Python implements Serializable{
    def script

    Python(script){
        this.script = script
    }

    def test_app(){
        script.sh ""
    }
}
