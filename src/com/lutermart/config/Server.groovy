package com.lutermart.config

class Server {
    String user;
    String address;
    String credentialsID
    List valid_keys = ['user', 'address', 'credentialsID']
    Validator validator = new Validator()
    Server(Map<String,String> params){
        validator.validate(valid_keys, params)
    }
}
