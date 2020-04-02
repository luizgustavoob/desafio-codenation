package br.com.luizgustavo.novodesafiocodenation.controller;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.com.luizgustavo.novodesafiocodenation.model.ResponseData;

public class RequestAPIController {

	private static final String token = "ab3fdfc2e11df782ba19434061e63c4cb3b50a7b";
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	public ResponseData getData() throws IOException, InterruptedException {
		String url = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token;

		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(request);

		System.out.println("Resposta getData(): " + response.getStatusLine().toString() + " - " + 
				EntityUtils.toString(response.getEntity()));

		ResponseData data = new Gson().fromJson(EntityUtils.toString(response.getEntity()), ResponseData.class);
		FileController.saveInFile(data);

		return data;
	}

	public void postData() throws IOException, InterruptedException {
		String url = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + token;

		FileBody file = new FileBody(FileController.getFile());
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("answer", file);
		HttpEntity entity = builder.build();

		HttpPost request = new HttpPost(url);
		request.setEntity(entity);

		CloseableHttpResponse response = httpClient.execute(request);

		System.out.println("Resposta postData(): " + response.getStatusLine().toString() + " - "
				+ EntityUtils.toString(response.getEntity()));
	}

}
