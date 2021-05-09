package entidade;

import java.util.List;

public class RetornoColaborador {
	private int codigoRetorno; // 0-Sucesso 1-Falha
	private String mensagemRetorno;
	private List<Colaborador> listaCliente;
	
	public int getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	public List<Colaborador> getListaCliente() {
		return listaCliente;
	}
	public void setListaCliente(List<Colaborador> listaCliente) {
		this.listaCliente = listaCliente;
	}

	

}
