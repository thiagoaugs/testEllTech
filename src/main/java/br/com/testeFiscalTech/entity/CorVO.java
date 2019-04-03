package br.com.testeFiscalTech.entity;


/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs
 * Classe que representa o objeto cor, utilizado pela classe veiculo
 */
public class CorVO {

	private int id;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
