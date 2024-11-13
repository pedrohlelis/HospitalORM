package services;

import java.util.List;
import model.Room;
import repository.RoomRepository;

public class RoomService {

    private final RoomRepository roomRepository;

   
    public RoomService() {
        this.roomRepository = new RoomRepository();
    }

    public boolean saveRoom(Room room) {
        if (room == null) {
            System.out.println("Erro: Sala inválida!");
            return false;
        }
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.getAllRooms();
        if (rooms == null || rooms.isEmpty()) {
            System.out.println("Nenhuma sala encontrada.");
        }
        return rooms;
    }

    public Room findRoomById(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Erro: ID inválido!");
            return null;
        }
        Room room = roomRepository.findById(id);
        if (room == null) {
            System.out.println("Sala não encontrada para o ID: " + id);
        }
        return room;
    }

    public boolean updateRoom(Long id, Room room) {
        if (id == null || id <= 0 || room == null) {
            System.out.println("Erro: Dados inválidos para atualização!");
            return false;
        }
        boolean updated = roomRepository.update(id, room);
        if (!updated) {
            System.out.println("Falha ao atualizar a sala com ID: " + id);
        }
        return updated;
    }

    public void deleteRoom(Long id) {
        if (id == null || id <= 0) {
            System.out.println("Erro: ID inválido para exclusão!");
            return;
        }
        roomRepository.delete(id);
        System.out.println("Sala removida com sucesso para o ID: " + id);
    }

    public void closeService() {
        roomRepository.close();
        System.out.println("Serviço de sala fechado.");
    }
}