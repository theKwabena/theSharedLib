package com.lutermart.config

class Validator {
    static void validate(List keys, Map params){
        params.keySet().forEach { key ->
            if(!keys.contains(key)){
                throw new IllegalArgumentException("Invalid Parameter $key")
            }
        }
    }
}
