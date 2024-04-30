package com.lutermart.config

class NginxWebApp implements Serializable{
    String appName;
    String configFile;
    String buildDirectory
    Validator validator = new Validator()
    List valid_keys = ['appName', 'configFile', 'buildDirectory']

    NginxWebApp(Map<String,String> app){
        validator.validate(valid_keys, app)
    }
}
