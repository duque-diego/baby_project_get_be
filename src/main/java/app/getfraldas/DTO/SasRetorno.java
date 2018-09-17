package app.getfraldas.DTO;

import java.io.Serializable;

public class SasRetorno implements Serializable {

	private String status;
	private String mensagem;

	public SasRetorno() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
