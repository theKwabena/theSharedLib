package com.lutermart.config


class DockerConfig{
    String registry;
    String credentials_id;

    DockerConfig(String registry, String credentials_id){
        this.registry = registry;
        this.credentials_id = credentials_id;
    }

}