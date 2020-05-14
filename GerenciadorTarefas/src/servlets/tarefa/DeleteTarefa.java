package servlets.tarefa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.Validation;
import model.Tarefa;
import service.TarefaService;

/**
 * Servlet implementation class DeleteTarefa
 */
@WebServlet(name = "DeleteTarefa.do", urlPatterns = { "/DeleteTarefa.do" })
public class DeleteTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario") == null) {
			request.getSession().setAttribute("message", "Usuário não autenticado");
			response.sendRedirect("login.jsp");
			return;
		}

		String id = request.getParameter("id");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(id)) {
			session.setAttribute("message", "Erro ao enviar o formulário.");
			response.sendRedirect("excluir-tarefa.jsp");
			return;
		}

		TarefaService tarefaService = new TarefaService();

		Tarefa tarefa = new Tarefa();
		tarefa.setId(Integer.parseInt(id));

		tarefaService.excluir(tarefa);

		session.setAttribute("message", "Tarefa excluída com sucesso!");
		response.sendRedirect("home.jsp");
		return;
		
	}

}
