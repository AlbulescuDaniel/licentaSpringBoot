package net.licenta.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class RegisterMailContentBuilder {
 
    private TemplateEngine templateEngine;
 
    @Autowired
    public RegisterMailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String build(String userName, String password) {
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("password", password);
        return templateEngine.process("registerMailTemplate", context);
    }
 
}