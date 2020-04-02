package br.com.luizgustavo.novodesafiocodenation.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.luizgustavo.novodesafiocodenation.model.ResponseData;

public class CryptoController {

	private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

	public void decrypt(ResponseData data) throws IOException {
		data.setCifrado(data.getCifrado().toLowerCase());

		String decrypted = "";
		for (int i = 0; i < data.getCifrado().length(); i++) {
			char letter = data.getCifrado().charAt(i);
			if (!Character.isLetter(letter)) {
				decrypted += letter;
				continue;
			}
			decrypted += getDecryptedLetter(letter, data.getNumero_casas());
		}
		data.setDecifrado(decrypted);

		FileController.saveInFile(data);
	}

	private char getDecryptedLetter(char letter, Integer numberToDecrease) {
		int indexDecryptedLetter = alphabet.indexOf(letter) - numberToDecrease;
		if (indexDecryptedLetter < 0) {
			indexDecryptedLetter = alphabet.length() - Math.abs(indexDecryptedLetter);
		}
		return alphabet.charAt(indexDecryptedLetter);
	}

	public void applyCryptoSha1(ResponseData data) throws NoSuchAlgorithmException, IOException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		messageDigest.update(data.getDecifrado().getBytes());
		byte[] digest = messageDigest.digest();
		data.setResumo_criptografico(hexaToString(digest));

		FileController.saveInFile(data);
	}

	private String hexaToString(byte[] digest) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < digest.length; i++) {
			int parteAlta = ((digest[i] >> 4) & 0xf) << 4;
			int parteBaixa = digest[i] & 0xf;

			if (parteAlta == 0) {
				builder.append('0');
			}

			builder.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		
		return builder.toString();
	}
}
