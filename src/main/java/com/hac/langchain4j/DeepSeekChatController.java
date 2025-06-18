package com.hac.langchain4j;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deepseek")
public class DeepSeekChatController {

    private final OpenAiChatModel model;

    @Autowired  // 明确标注依赖注入
    public DeepSeekChatController(OpenAiChatModel model) {
        this.model = model;
    }

    @GetMapping
    public String chat(@RequestParam(value = "message", defaultValue = "你好") String message) {
        // 底层依赖okHttpClient进行网络请求
        return model.generate(message);
    }
}
