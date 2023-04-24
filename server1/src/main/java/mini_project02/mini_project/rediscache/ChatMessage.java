package mini_project02.mini_project.rediscache;

import java.util.Date;

public class ChatMessage {
	
	private String text;
	private Date textDate;
	private String name;
	
	
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ChatMessage(String name, String text, Date textDate) {
		super();
		this.setName(name);
		this.text = text;
		this.textDate = textDate;
	}

	public Date getTextDate() {
		return textDate;
	}
	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}


	@Override
	public String toString() {
		return "[name=" + name + ", text=" + text + ", textDate=" + textDate + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
