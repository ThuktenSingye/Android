package com.example.openaichatbox;

public class ChatMessage {
    public static String SENT_BY_ME = "user";
    public static String SENT_BY_BOT = "system";
    private String role;
    private String content;
    public ChatMessage(String content, String role){
        this.content = content;
        this.role = role;
    }
    public void setRole(String role){
        this.role = role;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getRole(){
        return this.role;
    }
    public String getContent(){
        return this.content;
    }

}
