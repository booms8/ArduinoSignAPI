package fish.potato.api;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/message")
public class MessageService {
	private Gson gson = new Gson();
	private MessageController controller = new MessageController();
	
	@GET
	@Path("/get")
	public Response get() {
		try {
			return Response.ok(gson.toJson(controller.getMessage()))
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
			return Response.ok(gson.toJson(controller.getLastWriten()))
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
			String result = controller.update(message);
			return Response.ok(gson.toJson(result))
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
			String result = controller.update("");
			return Response.ok(gson.toJson(result))
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
