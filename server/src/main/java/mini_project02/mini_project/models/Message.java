package mini_project02.mini_project.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Message {
    private String username;
    private String messageText;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public JsonObject messageToJson() {
        return Json.createObjectBuilder()
                    .add("user", username)
                    .add("message", messageText)
                    .build();
    }

    public static Message messageFromRedis(JsonObject doc) {
        Message message = new Message();
        message.setMessageText(doc.getString("message"));
        message.setUsername(doc.getString("user"));
        return message;
    }
    
}
