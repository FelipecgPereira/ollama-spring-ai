package com.github.FelipecgPereira.Ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient
                .prompt()
//                .system("""
//                Voce é um assistente de receita de bolo de chocolate com cenoura. Suas regras sempre que o
//                chefe perguntar sobre a receita referente a bolo voce deve responder dando dicas de quantidade,
//                porçoes e acompanhamento.
//                Quando o usuario pedi ajuda com qualquer coisa diferente voce deve responder informando que é um assistente
//                com sua regras definidas
//                """)
                .user(message)
                .call()
                .content();
    }
}
