package com.lutermart.config

import com.lutermart.config.validator
class NginxHost {
    String configurationDirectory;
    String sitesDirectory;
    Server server
    static List valid_keys = [
            'configurationDirectory',
            'sitesDirectory',
            'server'
    ]
//    Validator validator = new Validator()

    NginxHost(Map<String,String> params){
        params.keySet().forEach { key ->
            if(!valid_keys.contains(key)){
                throw new IllegalArgumentException("Invalid Parameter $key")
            }
        }

        if(!params.server instanceof Server){
            throw new IllegalArgumentException("Please specify a valid server configuration")
        }

        params.forEach {
            key,value ->
                if(this.hasProperty(key)){
                    this."$key" = value
                }
        }
    }

}
