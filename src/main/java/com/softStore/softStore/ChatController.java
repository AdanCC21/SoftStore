package com.softStore.softStore;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage") // Cliente envía aquí los mensajes
    @SendTo("/topic/messages") // Mensajes retransmitidos a todos los suscriptores
    public String handleMessage(String message) {
        return message; // Simplemente retransmite el mensaje recibido
    }
}
