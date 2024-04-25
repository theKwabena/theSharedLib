package com.lutermart

import com.lutermart.config.BuildParams

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

//    def pushDockerImage(String imageName, String imageTag, String registry, String credentialsID){
//        script.echo "----- PUSHING DOCKER IMAGE TO $imageName -----"
//        script.docker.withRegistry(registry, credentialsID){
//            script.sh "docker push $imageName:$imageTag"
//        }
//     }
}
