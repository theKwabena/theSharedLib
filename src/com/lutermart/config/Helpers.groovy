package com.lutermart.config

import groovy.text.StreamingTemplateEngine

class Helpers {

    static def renderTemplate(binding, template){
        return new StreamingTemplateEngine().createTemplate(template).make(binding).toString()
    }

}
