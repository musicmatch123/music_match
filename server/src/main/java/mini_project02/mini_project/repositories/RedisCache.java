package mini_project02.mini_project.repositories;

import java.io.StringReader;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini_project02.mini_project.configs.RedisConfig;
import mini_project02.mini_project.models.Chat;
import mini_project02.mini_project.models.Message;

@Repository
public class RedisCache {

    @Autowired
    @Qualifier(RedisConfig.REDIS_CACHE)
    private RedisTemplate<String, String> redisTemplate;


    //Save the chat into redis for an hour
    public void saveChat(Chat chat) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(String.valueOf(chat.getId()), chat.chatToJson().toString(), Duration.ofHours(1));
    }

    public void deleteChat(int chatid) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        redisTemplate.opsForValue().getAndDelete(String.valueOf(chatid));

    }


    public Optional<Chat> getChat(int id) {
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(String.valueOf(id));
        if (value==null) {
            System.out.println("No chat history");
            return Optional.empty();
        }
        //read the input
        JsonReader reader = Json.createReader(new StringReader(value));
        JsonObject results = reader.readObject();
        Chat chatHistory = Chat.fromRedis(results);
        return Optional.of(chatHistory);
    }

    public void updateMessages(int id, String messageText, String user) {
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        Optional<Chat> optChat = getChat(id);
        Chat chat = optChat.get();
        Message message = new Message();
        message.setMessageText(messageText);
        message.setUsername(user);
        chat.addMessage(message);
        ops.set(String.valueOf(id), chat.chatToJson().toString(), Duration.ofHours(1));
    }
}
