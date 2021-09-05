package com.test.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser<T extends Object> {
	
	public T parse(String path, Class<T> clazz) throws IOException {

		if (clazz.getClassLoader().getResource(path) == null) {
			System.out.print("File not found or malformed : " + path);
		}
		String jsonPath = clazz.getClassLoader().getResource(path).getPath();
		
		if (jsonPath != null && jsonPath.startsWith("/")) {
			jsonPath = jsonPath.substring(1, jsonPath.length());
		}

		// read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get(jsonPath));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		return objectMapper.readValue(jsonData, clazz);

	}

}
