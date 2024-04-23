package com.lutermart

class Docker implements Serializable{
    def script

    Docker(script){
        this.script = script
    }

    def dockerLogin(String credentialsId, def repository=null){
        script.withCredentials([script.usernamePassword(
                'credentialsId': credentialsId,
                'passwordVariable': 'PASS',
                'usernameVariable': 'USER'
        )]){
            script.sh "echo $script.PASS | docker login $repository -u $script.USER --password-stdin"
            // This defaults to dockerhub if a repo name is not provided
        }
    }

    def buildDockerImage(String imageTag){
        script.echo "----- BUILDING DOCKER IMAGE -----"
        script.sh "docker build -t $imageTag ."
    }

    def pushDockerImage(String imageName){
        script.echo "----- PUSHING DOCKER IMAGE TO $imageName -----"
        script.sh "docker push $imageName"
    }
}
