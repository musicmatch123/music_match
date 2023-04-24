package mini_project02.mini_project.rediscache;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class ChatCacheController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    // private final ChatCacheRepository chatCacheRepository;

//     @Autowired
//     public ChatCacheController(ChatCacheRepository chatCacheRepository) {
//         this.chatCacheRepository = chatCacheRepository;
//     }

//    // @Cacheable(value = "chatCaches", key = "#chatId")
//     // @GetMapping(value = "/getCache/{chatId}")
//     // public List<ChatCache> getCache(@PathVariable String chatId) {
//     //     LOG.info("Getting chat with ID {}.", chatId);
//     //     //Optional<ChatCache> chatCache = chatCacheRepository.findById(Long.parseLong(chatId));
//     //     List<ChatCache> chatCache = chatCacheRepository.findAll();
//     //     if(!chatCache.isEmpty()) {
//     //     	return chatCache;
//     //     } else {
//     //     	return null;
//     //     }
//     // }


    
//     @Cacheable(value = "chatCaches")
//     @PostMapping("/addCache/{chatId}/{personName}/{text}")
//     public ChatCache addCacheByID(@PathVariable String text, @PathVariable String chatId, 
//     		@PathVariable String personName) {
//     	try {
// 			//LocalDateTime now = new LocalDateTu
// 			long millis = System.currentTimeMillis();  
// 			Date currentDate = new Date(millis);
			
// 			List<ChatMessage> messageList = new ArrayList<>();
// 			ChatMessage message1 = new ChatMessage();
// 			message1.setName(personName);
// 			message1.setText(text);
// 			message1.setTextDate(currentDate);
			
			
// 			messageList.add(message1);
// 			//messageList.add(message2);
			
// 			//ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
// 			//String messageJson = ow.writeValueAsString(messageList);
			
// 			ChatCache cache = new ChatCache(personName, text);
// 			//ChatCache pankaj = new ChatCache("Pankaj", 29000);
// 			//ChatCache lewis = new ChatCache("Lewis", 550);

// 			chatCacheRepository.saveAndFlush(cache);
// 			//userRepository.save(pankaj);
// 			//userRepository.save(lewis);
// 			LOG.info("Done saving chats. Data: {}.", chatCacheRepository.findAll());
// 			return cache;
			
// 		} catch (Exception e) {
// 			LOG.info("exception occurred :: {}", e.getMessage());
// 			e.getStackTrace();
// 			return null;
// 		}
    	
//     }

//     @CachePut(value = "chatCaches", key = "#chatCache.id")
//     @PutMapping("/update")
//     public ChatCache updateCacheByID(@RequestBody ChatCache chatCache) {
//     	chatCacheRepository.save(chatCache);
//         return chatCache;
//     }

//     @CacheEvict(value = "chatCaches", allEntries=true)
//     @DeleteMapping("/delete/{chatId}")
//     public void deleteUserByID(@PathVariable Long chatId) {
//         LOG.info("deleting cache with id {}", chatId);
//         Optional<ChatCache> chatCache = chatCacheRepository.findById(chatId);
//         if(chatCache.isPresent()) {
//         	chatCacheRepository.delete(chatCache.get());
//         }
//         //userRepository.delete(userId);
//     }
}
