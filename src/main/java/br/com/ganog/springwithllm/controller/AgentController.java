package br.com.ganog.springwithllm.controller;

import br.com.ganog.springwithllm.controller.model.MyQuestion;
import dev.langchain4j.model.chat.ChatLanguageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgentController {

//    @Value("${OPENAI_KEY}")
//    private String openAiKey;

    private final ChatLanguageModel chatLanguageModel;

    @PostMapping("/answer")
    public String chatOpenAi(@RequestBody MyQuestion myQuestion) {
        return chatLanguageModel.chat(myQuestion.question());
    }
}
