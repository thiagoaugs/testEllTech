package br.com.testeFiscalTech.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.testeFiscalTech.dao.CorDao;
import br.com.testeFiscalTech.dao.VeiculoDao;
import br.com.testeFiscalTech.entity.CorVO;
import br.com.testeFiscalTech.entity.VeiculoVO;

/**
 * @Criado em: 02/04/2019
 * @author thiagoaugs
 * Esta classe encapsula as regras de negocio do crud de veiculo
 */
@Local
@Stateless
public class VeiculoService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private CorDao corDAO;
	@Inject
	private VeiculoDao veiculoDAO;

	/**
	 * metodo utilizado para salvar um registro de veiculo
	 *
	 * @param VeiculoVO
	 */
	public void salvar(VeiculoVO vo) {

		VeiculoVO veiculoVO = null;
		CorVO corVO = null;

		try {
			corVO = corDAO.salvar(vo.getCor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (corVO != null) {
			try {
				vo.setCor(corVO);
				veiculoVO = veiculoDAO.salvar(vo);
				System.out.println("Salvou : " + veiculoVO.getPlaca());
			} catch (Exception e) {
				System.out.println("Erro ao salvar registro");
			}
		}

	}

	public List<VeiculoVO> listar() {

		List<VeiculoVO> lista = null;

		try {
			lista = veiculoDAO.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao listar");
		}

		return lista;
	}
}
