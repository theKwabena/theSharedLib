package com.lutermart.config

class DockerBuildParameters {
    String imageName;
    String imageTag;
    Boolean push;
   DockerConfig config;

    DockerBuildParameters(String imageName, DockerConfig config, String imageTag='latest', Boolean push =false){
        this.imageName = imageName;
        this.config = config;
        this.imageTag = imageTag
        this.push = push

        if (push && (config.registry =='' || config.credentials_id =='' )){
            throw new IllegalArgumentException("Registry endpoint and credentials must be provided when push is true")
        }
    }
}