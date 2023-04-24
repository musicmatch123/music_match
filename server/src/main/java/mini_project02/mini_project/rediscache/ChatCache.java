// package mini_project02.mini_project.rediscache;


// import java.io.Serializable;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.SequenceGenerator;

// @Entity
// public class ChatCache implements Serializable {

//     private static final long serialVersionUID = 7156526077883281623L;

//     @Id
//     @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_CHAT_CACHE", allocationSize = 1)
//     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
//     private Long id;
//     private String name;
// //    private long followers;
//     private String messages; 

//     public ChatCache() {
//     }

//     public ChatCache(String name, String messages) {
//         this.name = name;
//         this.messages = messages;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     @Override
//     public String toString() {
//         return String.format("User{id=%d, name='%s', messages=%s}", id, name, messages);
//     }

// 	public String getMessages() {
// 		return messages;
// 	}

// 	public void setMessages(String messages) {
// 		this.messages = messages;
// 	}
// }
