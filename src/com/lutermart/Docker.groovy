package com.lutermart


class DockerConfig {
    String registry;
    String credentials;

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

    BuildParams(String imageName, DockerConfig config, String imageTag, Boolean push){
        this.imageName = imageName;
        this.imageTag = imageTag ?: 'latest';
        this.config = config;
        this.push = push ?: false;

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
        def appImage = script.docker.build("$params.imageName:$params.imageTag")

        if (params.push) {
            script.docker.withRegistry(params.config.registry, params.config.credentials){
                appImage.push(params.imageTag)
            }

        }
    }

//    def pushDockerImage(String imageName, String imageTag, String registry, String credentialsID){
//        script.echo "----- PUSHING DOCKER IMAGE TO $imageName -----"
//        script.docker.withRegistry(registry, credentialsID){
//            script.sh "docker push $imageName:$imageTag"
//        }
//     }
}
