package com.lutermart.config

static def validate(List keys, Map params){
    params.keySet().forEach { key ->
        if(!keys.contains(key)){
        throw new IllegalArgumentException("Invalid Parameter $key")
        }
    }

//    params.values().forEach { value -> {
//        if(!value){
//            throw new IllegalArgumentException("Please specify $value")
//        }
//    }}
}

