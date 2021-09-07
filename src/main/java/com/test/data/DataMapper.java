package com.test.data;

import java.io.IOException;

import com.test.utils.JsonParser;

public class DataMapper{
	
	public static final String MAL_FORMED_FILE_ERROR = "File not found or malformed : ";
	public static final String MAPPED_KEY_NOTFOUND_EXCEPTION = "Data not found in the mapped data with the given key: ";

	private TestData testData;
	
	public void initMapper(String dataFilePath) {
		try {
			testData =  new JsonParser<TestData>().parse(dataFilePath, TestData.class);
		} catch (IOException e) {
			System.out.println(MAL_FORMED_FILE_ERROR + dataFilePath);
			e.printStackTrace();
		}
	}

	public String mapData(String inputData) {
		String value = null;
		if (inputData == null || testData == null || testData.isEmpty()) {
			return inputData;
		}

		value = testData.get(inputData);
		if (value == null) {
			System.out.println(MAPPED_KEY_NOTFOUND_EXCEPTION + inputData);
		}
		return value;
	}


}
