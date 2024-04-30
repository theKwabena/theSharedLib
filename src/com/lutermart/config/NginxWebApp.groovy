package com.lutermart.config

class NginxWebApp implements Serializable{
    String appName;
    String configFile;
    String buildDirectory
//    Validator validator = new Validator()
    List valid_keys = ['appName', 'configFile', 'buildDirectory']

    NginxWebApp(Map<String,String> params){
        params.keySet().forEach { key ->
            if(!valid_keys.contains(key)){
                throw new IllegalArgumentException("Invalid Parameter $key")
            }
        }

        params.forEach {
            key,value ->
                if(this.hasProperty(key)){
                    this."$key" = value
                }
        }
    }
}
