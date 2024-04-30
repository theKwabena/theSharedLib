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

    NginxHost(Map<String,String> params){
        validator.validate(valid_keys, params)

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
