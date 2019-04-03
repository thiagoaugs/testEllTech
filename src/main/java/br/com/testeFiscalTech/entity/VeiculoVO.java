package br.com.testeFiscalTech.entity;

import java.sql.Date;

/**
 * @author thiagoaugs Classe que representa o objeto veiculo
 */
public class VeiculoVO {

	private String placa;
	private String anoModelo;
	private Date atualizadoEm;
	private CorVO cor;
	private String anoFabricacao;
	private Boolean ativo = true;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}

	public CorVO getCor() {
		return cor;
	}

	public void setCor(CorVO cor) {
		this.cor = cor;
	}

	public String getAnoFabricacao() {
		return anoFabricacao;
	}

	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

}
