package com.lutermart


class DockerConfig {
    def script;;
    String registry;
    String credentials;

    DockerConfig(script, String registry, String credentials){
        this.script = script;
        this.registry = registry;
        this.credentials = credentials;
    }

}