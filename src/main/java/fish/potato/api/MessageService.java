package fish.potato.api;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import fish.potato.arduino.ArduinoLoader;
import fish.potato.arduino.util.ClassReader;
import fish.potato.arduino.util.FormatString;

@Path("/message")
public class MessageService {
	private Gson gson = new Gson();
	
	@GET
	@Path("/get")
	public Response get() {
		try {
			String message = FormatString.unformatMessage(ClassReader.read());
			return Response.ok(gson.toJson(message))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		}
	}
	
	@GET
	@Path("/time")
	public Response getTime() {
		try {
			Long time = FormatString.getTimeWritten(ClassReader.read());
			return Response.ok(gson.toJson(time))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		}
	}
	
	@GET
	@Path("/set/{message}")
	public Response set(@PathParam("message") String message) {
		try {
			ArduinoLoader.reprogram(message, 1);
			return Response.ok(gson.toJson("OK"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET")
					.build();
		}
	}
	
	@DELETE
	public Response erase() {
		try {
			ArduinoLoader.reprogram("", 1);
			return Response.ok(gson.toJson("OK"))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "DELETE")
					.build();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return Response.ok(gson.toJson(e.getMessage()))
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "DELETE")
					.build();
		}
	}
}
