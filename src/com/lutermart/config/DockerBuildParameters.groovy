package com.lutermart.config

class DockerBuildParameters {
    String imageName;
    String imageTag;
    Boolean push;
    DockerConfig config;
    List valid_keys = ['imageName', 'imageTag', 'push', 'config']

    DockerBuildParameters(Map params){
        validator.validate(valid_keys,params)
        if(!params.config instanceof DockerConfig){
            throw new IllegalArgumentException("Config invalid, please specify a valid config")
        }
        this.imageName = params.imageName;
        this.config = params.config as DockerConfig;
        this.imageTag = params.imageTag
        this.push = params.push

    }
}