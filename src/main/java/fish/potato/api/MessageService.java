package fish.potato.api;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import fish.potato.arduino.ArduinoLoader;

@Path("/message")
public class MessageService {
	private Gson gson = new Gson();

	@GET
	@Path("/{message}")
	public Response reload(@PathParam("message") String message) {
		try {
			ArduinoLoader.reprogram(message, 1);
			return Response.ok(gson.toJson("OK"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson("error"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
		}
	}
	
	@DELETE
	public Response erase() {
		try {
			ArduinoLoader.reprogram("", 1);
			return Response.ok(gson.toJson("OK"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson("error"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.build();
		}
	}
}
