package com.lutermart

import com.lutermart.config.DockerBuildParameters

class Docker implements Serializable{
    def script;

    Docker(script){
        this.script = script;
    }


//    def buildDockerImage(DockerBuildParameters params){
//        script.echo "----- BUILDING DOCKER IMAGE -----"
//        def appImage = script.docker.build("$params.imageName:$params.imageTag")
//
//
////        if (params.push) {
////            script.echo "push set to true, Trying to push docker image to provided repository"
////            script.docker.withRegistry(params.config.registry, params.config.credentials_id){
////                appImage.push(params.imageTag)
////            }
////
////        }
//        return appImage
//    }

    def buildDockerImage(DockerBuildParameters params){
        script.sh "docker build -t $params.imageName:$params.imageTag ."
    }
//
//    def pushDockerImage(image, String registry_url, String credentials_id, String tag = 'latest'){
//
//        script.docker.withRegistry(registry_url, credentials_id){
//            image.push(tag)
//        }
//        script.sh "docker push $imageName"
//    }

    def pushDockerImage(String image, String registry, String credentials_id){
        script.echo "----- PUSHING DOCKER IMAGE TO $registry -----"
        script.withCredentials([script.usernamePassword(
                'credentialsId': credentials_id,
                'passwordVariable': 'PASS',
                'usernameVariable': 'USER'
        )]){
            script.sh "echo $script.PASS | docker login $registry -u $script.USER --password-stdin"
            // This defaults to dockerhub if a repo name is not provided
        }
        script.sh "docker tag $image $registry/$image"
     }
}
