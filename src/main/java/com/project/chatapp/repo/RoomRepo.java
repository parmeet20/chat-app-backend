package com.project.chatapp.repo;

import com.project.chatapp.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepo extends MongoRepository<Room,String> {
    Room findByRoomId(String roomId);
}
