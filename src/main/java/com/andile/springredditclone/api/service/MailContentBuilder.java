package com.andile.springredditclone.api.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    @Autowired
    private final TemplateEngine templateEngine;

    /**
     * @param message
     * @return
     */
    public String build(String message){
        Context context = new Context();
        context.setVariable("message",message);
        return templateEngine.process("mailTemplate",context);
    }
}
