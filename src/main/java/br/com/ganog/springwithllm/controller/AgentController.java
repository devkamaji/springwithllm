package br.com.ganog.springwithllm.controller;

import br.com.ganog.springwithllm.controller.model.MyQuestion;
import br.com.ganog.springwithllm.controller.model.MyStructuredTemplate;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.ganog.springwithllm.controller.model.MyStructuredTemplate.*;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;

@RestController
@RequiredArgsConstructor
public class AgentController {

    @Value("${OPENAI_KEY}")
    private String openAiKey;

    @PostMapping("/answer")
    public String chatOpenAi(@RequestBody MyQuestion myQuestion) {

        ChatLanguageModel customModel = new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(openAiKey)
                .modelName(GPT_3_5_TURBO)
                .temperature(0.7)
                .build();

        return customModel.chat(myQuestion.question());
    }

    @PostMapping("/revenue")
    public String makeARecipe() {
        PromptDish promptDish = new PromptDish();
        promptDish.dish = "Cozido";
        promptDish.ingredients = List.of("batata", "cenoura", "carne", "cebola");

        Prompt prompt = StructuredPromptProcessor.toPrompt(promptDish);

        ChatLanguageModel customModel = new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(openAiKey)
                .modelName(GPT_3_5_TURBO)
                .temperature(0.7)
                .build();

        return customModel.chat(prompt.text());
    }
}
