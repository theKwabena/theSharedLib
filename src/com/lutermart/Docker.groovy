package com.lutermart

import com.lutermart.config.DockerBuildParameters

class Docker implements Serializable{
    def script;

    Docker(script){
        this.script = script;
    }


    def buildDockerImage(DockerBuildParameters params){
        script.echo "----- BUILDING DOCKER IMAGE -----"
        def appImage = script.docker.build("$params.imageName:$params.imageTag")

        if (params.push) {
            script.echo "push set to true, Trying to push docker image to provided repository"
            script.docker.withRegistry(params.config.registry, params.config.credentials_id){
                appImage.push(params.imageTag)
            }

        }
        return appImage
    }

//    def buildDockerImage(String imageName, String imageTag){
//        script.sh "docker build -t $imageName:$imageTag ."
//    }
//
//    def pushDockerImage(image, String registry_url, String credentials_id, String tag = 'latest'){
//
//        script.docker.withRegistry(registry_url, credentials_id){
//            image.push(tag)
//        }
//        script.sh "docker push $imageName"
//    }

    def pushDockerImage(image, String tag,  String registry, String credentials_id){
        script.echo "----- PUSHING DOCKER IMAGE TO $registry -----"
        script.docker.withRegistry(registry, credentials_id){
            tag ? image.push(tag) : image.push()
        }
     }
}
