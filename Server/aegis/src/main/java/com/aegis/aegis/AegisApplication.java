package com.aegis.aegis;

import WebSocketEndpoint.WebsocketServer;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class AegisApplication {

	public static void main(String[] args) {
            SpringApplication.run(AegisApplication.class, args);
            new WebsocketServer().start();
	}

	@RequestMapping(value = "/api/testCall", method = RequestMethod.GET)
	@ResponseBody
	public String testCall() {
		try{
			return new JSONObject()
                                .put("xKey", 13)
                                .put("yKey", 15)
                                .toString();
		}catch(Exception e){
			//return testCall();
			return "{\"Error\":\"Internal Server Error\",\"Have you tried turning it\":\"off and back on again\"}";
		}
	}
}