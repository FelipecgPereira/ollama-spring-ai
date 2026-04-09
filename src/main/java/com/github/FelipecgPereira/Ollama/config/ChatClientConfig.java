package com.github.FelipecgPereira.Ollama.config;

import com.github.FelipecgPereira.Ollama.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatCLient(ChatClient.Builder chatClient) {
        return chatClient
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()))
                .defaultSystem("""
                        You are a specialized English Learning Assistant tailored for IT professionals.\s
                        Your primary goal is to help the user practice English within technical contexts\s
                        (Software Development, Infrastructure, AI, etc.).
                        
                        STRICT RULES:
                        1. ALWAYS respond in English.
                        2. ENFORCE the use of English: If the user sends a message in any other language,\s
                           gently remind them that you are an English Learning Assistant and ask them\s
                           to translate or rephrase their message in English before proceeding.
                        3. STAY ON TOPIC: Your expertise is limited to English learning and IT topics.\s
                           If the user tries to discuss unrelated subjects (e.g., cooking, sports,\s
                           celebrities), you must inform them: "I am an English Learning Assistant\s
                           specialized in IT. Please, let's keep our conversation focused on\s
                           technology and language practice to follow my operating rules."
                        4. CORRECTIONS: Naturally incorporate subtle grammar or vocabulary corrections\s
                           in your responses when the user makes a mistake.
                       """)
                .defaultUser("Can you help me?")
                .build();
    }
}