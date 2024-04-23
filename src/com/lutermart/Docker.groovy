package com.lutermart

import java.rmi.registry.Registry


class DockerConfig {
    String registry
    String credentials

    DockerConfig(String registry, String credentials){
        this.registry = registry;
        this.credentials = credentials;
    }

}

class BuildParams {
    String imageName;
    String imageTag;
    Boolean push;
    DockerConfig config;

    BuildParams(String imageName, String imageTag='latest', Boolean push=false, DockerConfig config){
        this.imageName = imageName;
        this.imageTag = imageTag;
        this.config = config;
        this.push = push

        if (push && (config.registry =='' || config.credentials =='' )){
            throw new IllegalArgumentException("Registry endpoint and credentials must be provided when push is true")
        }
    }
}

class Docker implements Serializable{
    def script;

    Docker(script){
        this.script = script;
    }


    def buildDockerImage(BuildParams params){
        script.echo "----- BUILDING DOCKER IMAGE -----"
        def appImage = script.docker.build("$imageName:$imageTag")

        if (push) {
            appImage.push(imageTag)
        }
    }

    def pushDockerImage(String imageName, String imageTag, String registry, String credentialsID){
        script.echo "----- PUSHING DOCKER IMAGE TO $imageName -----"
        script.docker.withRegistry(registry, credentialsID){
            script.sh "docker push $imageName:$imageTag"
        }
     }
}
