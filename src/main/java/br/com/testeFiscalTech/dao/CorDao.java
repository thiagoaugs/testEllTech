package br.com.testeFiscalTech.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.testeFiscalTech.entity.CorVO;

/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs 
 * Esta classe encapsula os metodos do dao de cor.
 */
public class CorDao extends GenericDAO {

	public CorDao() {
	}

	public CorVO salvar(CorVO cad) throws Exception {
		Connection conn = null;
		CorVO objSalvo = new CorVO();
		try {
			conn = this.getConnection();

			if (cad.getId() > 0) {
				String query = " INSERT INTO cad_cor (descricao) VALUES (?);  ";

				List params = new ArrayList();
				params.add(cad.getDescricao());

				int id = this.executeUpdateId(conn, query, params, true);
				objSalvo.setId(id);
				objSalvo.setDescricao(cad.getDescricao());
			}else{
				String query = "  UPDATE cad_cor SET descricao = ? WHERE id = ?   ";

				List params = new ArrayList();
				params.add(cad.getDescricao());
				params.add(cad.getId());

				this.executeUpdate(conn, query, params, true);
				objSalvo.setId(cad.getId());
				objSalvo.setDescricao(cad.getDescricao());
			}
		} catch (Exception e) {
			System.out.println("Erro ao inserir : " + e.getMessage());
		}

		return objSalvo;
	}

}
