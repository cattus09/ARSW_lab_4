package edu.eci.arsw.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public GrammarChecker grammarChecker() {
        return new GrammarChecker();
    }
    @Primary
    @Bean
    public SpellChecker englishSpellChecker() {
        return new EnglishSpellChecker();
    }

     
    @Bean
    public SpellChecker spanishSpellChecker() {
        return new SpanishSpellChecker();
    }
}