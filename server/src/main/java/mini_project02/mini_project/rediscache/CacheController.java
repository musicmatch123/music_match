package mini_project02.mini_project.rediscache;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini_project02.mini_project.models.Chat;
import mini_project02.mini_project.models.Message;
import mini_project02.mini_project.repositories.RedisCache;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CacheController {

    @Autowired
    private RedisCache redisCache;

    @GetMapping(path="/getMessages") 
    public ResponseEntity<String> getMessages(@RequestParam int id) {
        Optional<Chat> optChat = redisCache.getChat(id);
        if (optChat.isEmpty()) {
            String message = "No message history";
            JsonObject body = Json.createObjectBuilder()
                                .add("message", message)
                                .build();
            return ResponseEntity.ok(body.toString());

        }

        Chat chat = optChat.get();
        return ResponseEntity.ok(chat.chatToJson().toString());
    }

  //  @CacheEvict(value = "chatCaches", allEntries=true)
  // @DeleteMapping(path="/delete/{chatId}")
  //  public void deleteChatByID(@PathVariable Long chatId) {
   //     LOG.info("deleting cache with id {}", chatId);
   //     Optional<ChatCache> chatCache = chatCacheRepository.findById(chatId);
   //      if(chatCache.isPresent()) {
   //     	chatCacheRepository.delete(chatCache.get());
   //     }
         //userRepository.delete(userId);
   // }

    @DeleteMapping(path="/delete/{chatId}")
        public ResponseEntity<String> DeleteMsg(@PathVariable int chatId ){

        redisCache.deleteChat(chatId);


        return ResponseEntity.ok( "removed");
    }


    
    @PostMapping(path="/saveMessageHistory")
    public ResponseEntity<String> saveMessageHistory(@RequestBody String payload) {
        //process payload
        System.out.println(payload);
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        int id = json.getInt("id");
        String user = json.getString("username");
        String messageText = json.getString("message");

        //retrieve chat from cache
        Optional<Chat> optChat = redisCache.getChat(id);

        //if empty create new chat
        if (optChat.isEmpty()) {
            Chat chat = new Chat();
            chat.setId(id);
            List<Message> messages = new LinkedList<>();
            Message message = new Message();
            message.setUsername(user);
            message.setMessageText(messageText);
            messages.add(message);
            chat.setMessages(messages);
            System.out.println("no current chat exists");
            redisCache.saveChat(chat);
            JsonObject response = Json.createObjectBuilder()
                                .add("message", "new chat saved!")
                                .build();
            return ResponseEntity.ok(response.toString());
        } else {
            //if chat history exists, update existing hustory
            redisCache.updateMessages(id, messageText, user);
            JsonObject response = Json.createObjectBuilder()
                                .add("message", "message cached")
                                .build();
            return ResponseEntity.ok(response.toString());
        }

        



    }
    
}
