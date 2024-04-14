package com.chat.chatapp.controllers;

import com.chat.chatapp.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    //@MessageMapping tells what is the url to invoke this sendMessage
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") //to which topic send to
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage)
    {

        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor)
    {

        //add username in websocket session
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;

    }
}
