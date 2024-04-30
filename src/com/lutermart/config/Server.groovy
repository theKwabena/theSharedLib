package com.lutermart.config

class Server {
    String user;
    String address;
    String credentialsID
    List valid_keys = ['user', 'address', 'credentialsID']
//    Validator validator = new Validator()
    Server(Map<String,String> params){
//        validator.validate(valid_keys, params)
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
