package br.com.luizgustavo.novodesafiocodenation;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import br.com.luizgustavo.novodesafiocodenation.controller.CryptoController;
import br.com.luizgustavo.novodesafiocodenation.controller.RequestAPIController;
import br.com.luizgustavo.novodesafiocodenation.model.ResponseData;

public class ChallengeCodenation {

	public static void main(String[] args) {
		ChallengeCodenation challenge = new ChallengeCodenation();
		challenge.run();
	}

	private void run() {
		try {
			RequestAPIController requestController = new RequestAPIController();
			CryptoController cryptoController = new CryptoController();
			
			ResponseData data = requestController.getData();
			cryptoController.decrypt(data);
			cryptoController.applyCryptoSha1(data);
			
			requestController.postData();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
