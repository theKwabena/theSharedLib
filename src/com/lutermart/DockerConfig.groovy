package com.lutermart


class DockerConfig implements Serializable{
    Script script;;
    String registry;
    String credentials;

    DockerConfig(script, String registry, String credentials){
        this.script = script;
        this.registry = registry;
        this.credentials = credentials;
    }

}