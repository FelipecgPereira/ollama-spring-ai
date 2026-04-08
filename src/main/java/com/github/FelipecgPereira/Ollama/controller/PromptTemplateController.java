package com.github.FelipecgPereira.Ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {
    private final ChatClient chatClient;

    public PromptTemplateController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    private Resource userPromtTemplate;

    @GetMapping("/email")
    public String email(@RequestParam("customerName") String customerName,@RequestParam("customerMessage") String customerMessage) {
        return chatClient
                .prompt()
                .system("""
                       You are a professional customer service assistent which helps drafting email responses
                        to improve the productivity of the customer support team
                       """)
                .user(promptTemplateSpec -> promptTemplateSpec.text(userPromtTemplate)
                        .param("customerName",customerName)
                        .param("customerMessage",customerMessage)
                )
                .call()
                .content();
    }
}