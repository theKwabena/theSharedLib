package com.lutermart

class Docker implements Serializable{
    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageTag){
        script.sh "docker build -t $imageTag ."
    }

    def pushDockerImage(String imageName){
        script.sh "docker push $imageName"
    }
}
