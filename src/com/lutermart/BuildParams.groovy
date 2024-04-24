package com.lutermart

class BuildParams implements Serializable {
    Script script;
    String imageName;
    String imageTag;
    Boolean push;
    DockerConfig config;

    BuildParams(script, String imageName, DockerConfig config, String imageTag, Boolean push){
        this.script = script;
        this.imageName = imageName;
        this.imageTag = imageTag ?: 'latest';
        this.config = config;
        this.push = push ?: false;

        if (push && (config.registry =='' || config.credentials =='' )){
            throw new IllegalArgumentException("Registry endpoint and credentials must be provided when push is true")
        }
    }
}