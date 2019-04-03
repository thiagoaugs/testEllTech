package br.com.testeFiscalTech.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.testeFiscalTech.entity.VeiculoVO;

/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs 
 * Esta classe encapsula os metodos do dao de veiculo.
 */
public class VeiculoDao extends GenericDAO {

	public VeiculoDao() {
	}

	public VeiculoVO salvar(VeiculoVO cad) throws Exception {
		Connection conn = null;
		VeiculoVO objSalvo = new VeiculoVO();
		try {
			conn = this.getConnection();

			if (cad.getAtualizadoEm() == null) {
				Date d1 = new Date();
				cad.setAtualizadoEm((java.sql.Date) d1);

				String query = " INSERT INTO  cad_veiculo (placa, ano_modelo, atualizadoEm, id_cor, ano_fabricacao, ativo) VALUES (?,?,?,?,?,?);  ";

				List params = new ArrayList();
				params.add(cad.getPlaca());
				params.add(cad.getAnoModelo());
				params.add(cad.getAtualizadoEm());
				params.add(cad.getCor().getId());
				params.add(cad.getAnoFabricacao());
				params.add(cad.getAtivo());

				this.executeUpdate(conn, query, params, true);
				objSalvo.setPlaca(cad.getPlaca());
				objSalvo.setAnoModelo(cad.getAnoModelo());
				objSalvo.setAtualizadoEm(cad.getAtualizadoEm());
				objSalvo.getCor().setId(cad.getCor().getId());
				objSalvo.getCor().setDescricao(cad.getCor().getDescricao());
				objSalvo.setAnoFabricacao(cad.getAnoFabricacao());
				objSalvo.setAtivo(cad.getAtivo());
			} else {

				Date d1 = new Date();
				cad.setAtualizadoEm((java.sql.Date) d1);

				String query = " UPDATE cad_veiculo SET placa = ?, ano_modelo = ?, atualizadoEm = ?, id_cor = ?, ano_fabricacao = ?, ativo = ?  "
						+ "WHERE placa = ? ";

				List params = new ArrayList();
				params.add(cad.getPlaca());
				params.add(cad.getAnoModelo());
				params.add(cad.getAtualizadoEm());
				params.add(cad.getCor().getId());
				params.add(cad.getAnoFabricacao());
				params.add(cad.getAtivo());
				params.add(cad.getPlaca());

				this.executeUpdate(conn, query, params, true);

				objSalvo.setPlaca(cad.getPlaca());
				objSalvo.setAnoModelo(cad.getAnoModelo());
				objSalvo.setAtualizadoEm(cad.getAtualizadoEm());
				objSalvo.getCor().setId(cad.getCor().getId());
				objSalvo.getCor().setDescricao(cad.getCor().getDescricao());
				objSalvo.setAnoFabricacao(cad.getAnoFabricacao());
				objSalvo.setAtivo(cad.getAtivo());
			}

		} catch (Exception e) {
			System.out.println("Erro ao inserir : " + e.getMessage());
		}
		return objSalvo;
	}

	@SuppressWarnings("unchecked")
	public List<VeiculoVO> listar() throws Exception  {
		List<VeiculoVO> lista = null;

		Connection conn = null;
		try {
			conn = this.getConnection();
			String query = " SELECT * FROM cad_veiculo  ";
			lista =  this.listar(conn, query, null, true);
		} catch (Exception e) {
			System.out.println("Erro ao listar : " + e.getMessage());
		}
		return lista;
	}
}
