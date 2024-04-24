package com.lutermart


class DockerConfig {
    String registry;
    String credentials;

    DockerConfig(String registry, String credentials){
        this.registry = registry;
        this.credentials = credentials;
    }

}