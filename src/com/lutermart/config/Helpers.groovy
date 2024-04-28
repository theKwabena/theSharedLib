package com.lutermart.config

import groovy.text.StreamingTemplateEngine

class Helpers {

    def renderTemplate(Map binding, String template){
        return new StreamingTemplateEngine().createTemplate(template).make(binding).toString()
    }

}
