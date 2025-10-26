package com.project.chatapp.controller;

import com.project.chatapp.entities.Message;
import com.project.chatapp.entities.Room;
import com.project.chatapp.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    // Create a new room
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) throws Exception {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    // Get a room by its MongoDB ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(roomService.getRoomById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    // Delete a room by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    // Update a room
    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    // Get all messages for a room by roomId
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessagesByRoomId(@PathVariable String roomId) {
        try {
            return ResponseEntity.ok(roomService.getMessagesByRoomId(roomId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Join a room by roomId
    @PostMapping("/join/{roomId}")
    public ResponseEntity<Room> joinRoom(@PathVariable String roomId) {
        try {
            return ResponseEntity.ok(roomService.joinRoom(roomId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
