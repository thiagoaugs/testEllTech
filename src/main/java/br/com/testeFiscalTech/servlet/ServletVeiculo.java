package br.com.testeFiscalTech.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.testeFiscalTech.entity.CorVO;
import br.com.testeFiscalTech.entity.VeiculoVO;
import br.com.testeFiscalTech.service.VeiculoService;

/**
 * @Criado em: 03/04/2019
 * @author thiagoaugs
 * 
 *         Servlet implementation class ServeletVeiculo
 */

public class ServletVeiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	@EJB
	private VeiculoService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletVeiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.setContext(config.getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		System.out.println(action);

		if (action.equals("salvar")) {
			VeiculoVO vo = new VeiculoVO();
			CorVO corVo = new CorVO();

			vo.setPlaca(request.getParameter("placa"));
			vo.setAnoModelo(request.getParameter("anoModelo"));
			corVo.setDescricao(request.getParameter("cor"));
			vo.setCor(corVo);
			vo.setAnoFabricacao(request.getParameter("anoFabricacao"));

			service.salvar(vo);

		}
		if (action.equals("listar")) {

			// faço a pesquisa e depois
			ArrayList<VeiculoVO> resultadoDaPesquisa = (ArrayList<VeiculoVO>) service
					.listar();

			request.getSession().setAttribute("resultado", resultadoDaPesquisa);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

}
