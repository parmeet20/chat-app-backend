package com.project.chatapp.service.impl;

import com.project.chatapp.entities.Message;
import com.project.chatapp.entities.Room;
import com.project.chatapp.repo.RoomRepo;
import com.project.chatapp.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;

    @Override
    public Room createRoom(Room room) throws Exception {
        Room prev = roomRepo.findByRoomId(room.getRoomId());
        if (prev != null) {
            throw new RuntimeException("Room already exists");
        }
        room.setTimestamps(LocalDateTime.now());
        return roomRepo.save(room);
    }

    @Override
    public Room getRoomById(String id) throws Exception {
        return roomRepo.findById(id)
                .orElseThrow(() -> new Exception("Room not found with id: " + id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public void deleteRoom(String id) {
        roomRepo.deleteById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepo.save(room); // Same as create if the room with the given ID exists
    }

    @Override
    public List<Message> getMessagesByRoomId(String roomId) throws Exception {
        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            throw new RuntimeException("Room not found");
        }
        return room.getMessages();
    }

    @Override
    public Room joinRoom(String roomId) throws Exception {
        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            throw new RuntimeException("Room not found");
        }
        return room;
    }
}
