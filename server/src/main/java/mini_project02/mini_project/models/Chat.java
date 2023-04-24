package mini_project02.mini_project.models;

import java.util.LinkedList;
import java.util.List;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Chat { 
    private int id;
    private List<Message> messages = new LinkedList<>();
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public JsonObject chatToJson() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Message message: messages) {
            arrayBuilder.add(message.messageToJson());
        }
        JsonArray jsonMessages = arrayBuilder.build();

        return Json.createObjectBuilder()
                    .add("id", id)
                    .add("messages", jsonMessages)
                    .build();
    }

    public static Chat fromRedis(JsonObject doc) {
        final Chat chat = new Chat();
        JsonArray messagesJson = doc.getJsonArray("messages");
        List<Message> messages = new LinkedList<>();
        for (int i=0; i< messagesJson.size(); i++) {
            messages.add(Message.messageFromRedis(messagesJson.getJsonObject(i)));
        }
        chat.setId(doc.getInt("id"));
        chat.setMessages(messages);
        return chat;

    }
}
