package Mental_Health_Simulation; 

import okhttp3.*;

import java.io.IOException;

public class OpenAIMoodEvaluator {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String evaluateMood(String mood) {
        OkHttpClient client = new OkHttpClient();
        String prompt = "The user is feeling " + mood + ". Suggest a motivational quote and a calming mental health activity.";

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"user\", \"content\": \"" + prompt + "\"}\n" +
                "  ]\n" +
                "}";

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            int start = result.indexOf("\"content\":\"") + 11;
            int end = result.indexOf("\"", start);
            return result.substring(start, Math.min(end, result.length())).replace("\\n", "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Could not connect to OpenAI API.";
        }
    }
}