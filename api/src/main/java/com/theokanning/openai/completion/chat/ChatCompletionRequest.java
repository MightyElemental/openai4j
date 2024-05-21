package com.theokanning.openai.completion.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.assistants.run.ToolChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatCompletionRequest {


    /**
     * ID of the model to use.
     */
    String model;

    /**
     * The messages to generate chat completions for, in the <a
     * href="https://platform.openai.com/docs/guides/chat/introduction">chat format</a>.<br>
     */
    List<ChatMessage> messages;

    /**
     * Must be either 'text' or 'json_object'. <br>
     * When specifying 'json_object' as the request format it's still necessary to instruct the model to return JSON.
     */
    @JsonProperty("response_format")
    ChatResponseFormat responseFormat;

    /**
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower
     * values like 0.2 will make it more focused and deterministic.<br>
     * We generally recommend altering this or top_p but not both.
     */
    Double temperature;

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens
     * with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.<br>
     * We generally recommend altering this or temperature but not both.
     */
    @JsonProperty("top_p")
    Double topP;

    /**
     * How many chat completion chatCompletionChoices to generate for each input message.
     */
    Integer n;

    /**
     * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only <a
     * href="https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format">server-sent
     * events</a> as they become available, with the stream terminated by a data: [DONE] message.
     */
    Boolean stream;

    /**
     * If set, partial message deltas will be sent, like in ChatGPT.
     * Tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE]
     */
    @JsonProperty("stream_options")
    StreamOption streamOptions;

    /**
     * Up to 4 sequences where the API will stop generating further tokens.
     */
    List<String> stop;

    /**
     * The maximum number of tokens allowed for the generated answer. By default, the number of tokens the model can return will
     * be (4096 - prompt tokens).
     */
    @JsonProperty("max_tokens")
    Integer maxTokens;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far,
     * increasing the model's likelihood to talk about new topics.
     */
    @JsonProperty("presence_penalty")
    Double presencePenalty;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far,
     * decreasing the model's likelihood to repeat the same line verbatim.
     */
    @JsonProperty("frequency_penalty")
    Double frequencyPenalty;

    /**
     * Accepts a json object that maps tokens (specified by their token ID in the tokenizer) to an associated bias value from -100
     * to 100. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will
     * vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100
     * should result in a ban or exclusive selection of the relevant token.
     */
    @JsonProperty("logit_bias")
    Map<String, Integer> logitBias;


    /**
     * A unique identifier representing your end-user, which will help OpenAI to monitor and detect abuse.
     */
    String user;

    /**
     * @since 0.20.5 {@link com.theokanning.openai.completion.chat.ChatFunction} {@link  com.theokanning.openai.completion.chat.ChatFunctionDynamic}will be deprecated
     * @deprecated Replaced by {@link #tools}
     * recommend to use {@link com.theokanning.openai.function.FunctionDefinition}  or custom class
     */
    @Deprecated
    List<?> functions;

    /**
     * Controls how the model responds to function calls, as specified in the <a href="https://platform.openai.com/docs/api-reference/chat/create#chat/create-function_call">OpenAI documentation</a>.
     */
    @JsonProperty("function_call")
    @Deprecated
    ChatCompletionRequestFunctionCall functionCall;


    /**
     * This feature is in Beta. If specified, our system will make a best effort to sample deterministically, such that repeated requests with the same seed and parameters should return the same result. Determinism is not guaranteed, and you should refer to the system_fingerprint response parameter to monitor changes in the backend.
     */
    Integer seed;

    /**
     * Whether to return log probabilities of the output tokens or not. If true, returns the log probabilities of each output token returned in the content of message.
     */
    Boolean logprobs;

    /**
     * An integer between 0 and 20 specifying the number of most likely tokens to return at each token position, each with an associated log probability. logprobs must be set to true if this parameter is used.
     */
    @JsonProperty("top_logprobs")
    Integer topLogprobs;


    /**
     * Function definition, only used if type is "function"
     * recommend to use {@link com.theokanning.openai.function.FunctionDefinition}  or custom class
     *
     * @since 0.20.5 {@link com.theokanning.openai.completion.chat.ChatFunction} {@link  com.theokanning.openai.completion.chat.ChatFunctionDynamic}will be deprecated
     */
    List<ChatTool> tools;

    /**
     * Controls which (if any) function is called by the model. none means the model will not call a function and instead generates a message. auto means the model can pick between generating a message or calling a function.
     */
    @JsonProperty("tool_choice")
    ToolChoice toolChoice;


}
