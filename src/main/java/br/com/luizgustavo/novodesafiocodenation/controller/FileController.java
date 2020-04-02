package br.com.luizgustavo.novodesafiocodenation.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.JsonIOException;

import br.com.luizgustavo.novodesafiocodenation.model.ResponseData;

public class FileController {

	public static void saveInFile(ResponseData data) throws JsonIOException, IOException {
		JSONObject json = buildJson(data);
		FileWriter fileWritter = getFileWritter();
		fileWritter.write(json.toString());
		fileWritter.flush();
		fileWritter.close();
	}
	
	public static File getFile() throws IOException {
		return new File(getPathJSONFile());
	}

	private static JSONObject buildJson(ResponseData data) {
		JSONObject json = new JSONObject();
		json.put("numero_casas", data.getNumero_casas());
		json.put("token", data.getToken());
		json.put("cifrado", data.getCifrado());
		json.put("decifrado", data.getDecifrado());
		json.put("resumo_criptografico", data.getResumo_criptografico());
		return json;
	}

	private static FileWriter getFileWritter() throws IOException {
		String path = getPathJSONFile();
		return new FileWriter(path);
	}

	private static String getPathJSONFile() throws IOException {
		return new File("...").getCanonicalPath().concat("answer.json");
	}
}
