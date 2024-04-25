package com.lutermart.config


class DockerConfig{
    String registry;
    String credentials_id;

    List valid_keys = ['registry', 'credentials_id']

    DockerConfig(Map params){
        validator.validate(valid_keys,params)

        this.registry = params.registry
        this.credentials_id = params.credentials_id

        if ((registry =='' || credentials_id =='' )){
            throw new IllegalArgumentException("Registry endpoint and credentials must be provided when push is true")
        }

    }

}