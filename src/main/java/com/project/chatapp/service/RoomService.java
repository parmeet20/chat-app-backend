package com.project.chatapp.service;

import com.project.chatapp.entities.Message;
import com.project.chatapp.entities.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room) throws Exception;

    Room getRoomById(String id) throws Exception;

    List<Room> getAllRooms();

    void deleteRoom(String id);

    Room updateRoom(Room room);

    // New methods
    List<Message> getMessagesByRoomId(String roomId) throws Exception;

    Room joinRoom(String roomId) throws Exception;
}
