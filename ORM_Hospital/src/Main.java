import model.Room;
import services.RoomService;

public class Main {

	public static void main(String[] args) {
		Room r = new Room("18","leito", 5, true);
		RoomService service = new RoomService();
		
		service.saveRoom(r);
	}

}
