package com.project.chatapp.controller;

import com.project.chatapp.entities.Message;
import com.project.chatapp.entities.Room;
import com.project.chatapp.payload.MessageRequest;
import com.project.chatapp.repo.RoomRepo;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@CrossOrigin
public class ChatController {

    private final RoomRepo roomRepo;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest messageRequest
    ) {
        Room room = roomRepo.findByRoomId(roomId);

        if (room == null) {
            throw new RuntimeException("Room not found with roomId: " + roomId);
        }

        Message newMessage = new Message(messageRequest.getSender(), messageRequest.getContent(), LocalDateTime.now());
        room.getMessages().add(newMessage);
        roomRepo.save(room); // Persist updated messages

        return newMessage;
    }
}
