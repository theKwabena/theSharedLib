package com.lutermart.config

import groovy.text.SimpleTemplateEngine

class Helpers {

    static def renderTemplate(Map binding, String template){
        return new SimpleTemplateEngine().createTemplate(template).make(binding).toString()
    }

}
