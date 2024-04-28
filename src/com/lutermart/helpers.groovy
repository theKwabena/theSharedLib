package com.lutermart

import groovy.text.SimpleTemplateEngine

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


