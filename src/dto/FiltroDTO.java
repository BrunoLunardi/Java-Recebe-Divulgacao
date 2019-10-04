package dto;

public class FiltroDTO {
	
	int id, id_topico, id_user, id_acao;
	Double valor_min, valor_max;
	
	public FiltroDTO(int id_topico, int id_user, int id_acao,
			Double valor_min, Double valor_max) {
		this.id_topico = id_topico;
		this.id_user = id_user;
		this.id_acao = id_acao;
		this.valor_min = valor_min;
		this.valor_max = valor_max;
	}
	
	public int getId_topico() {
		return id_topico;
	}
	public void setId_topico(int id_topico) {
		this.id_topico = id_topico;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_acao() {
		return id_acao;
	}
	public void setId_acao(int id_acao) {
		this.id_acao = id_acao;
	}
	public Double getValor_min() {
		return valor_min;
	}
	public void setValor_min(Double valor_min) {
		this.valor_min = valor_min;
	}
	public Double getValor_max() {
		return valor_max;
	}
	public void setValor_max(Double valor_max) {
		this.valor_max = valor_max;
	}
	
	

}
