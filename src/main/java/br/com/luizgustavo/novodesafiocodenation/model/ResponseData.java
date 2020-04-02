package br.com.luizgustavo.novodesafiocodenation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

	private Integer numero_casas;
	private String token;
	private String cifrado;
	private String decifrado;
	private String resumo_criptografico;
}
