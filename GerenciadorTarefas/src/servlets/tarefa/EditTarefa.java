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
import model.Usuario;
import service.TarefaService;

/**
 * Servlet implementation class EditTarefa
 */
@WebServlet(name = "EditTarefa.do", urlPatterns = { "/EditTarefa.do" })
public class EditTarefa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario") == null) {
			request.getSession().setAttribute("message", "Usuário não autenticado");
			response.sendRedirect("login.jsp");
			return;
		}

		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(id)) {
			session.setAttribute("message", "Erro ao enviar o formulário.");
			response.sendRedirect("editar-tarefa.jsp");
			return;
		}

		if (!v.isValidString(titulo)) {
			session.setAttribute("message", "Os campos de título e descrição são obrigatórios.");
			response.sendRedirect("editar-tarefa.jsp");
			return;
		}

		if (!v.isValidString(descricao)) {
			session.setAttribute("message", "Os campos de título e descrição são obrigatórios.");
			response.sendRedirect("editar-tarefa.jsp");
			return;
		}

		TarefaService tarefaService = new TarefaService();

		Tarefa tarefa = new Tarefa();
		tarefa.setId(Integer.parseInt(id));
		tarefa.setTitulo(titulo);
		tarefa.setDescricao(descricao);
		tarefa.setUsuario((Usuario) session.getAttribute("usuario"));

		tarefaService.alterar(tarefa);

		session.setAttribute("message", "Tarefa atualizada com sucesso!");
		response.sendRedirect("home.jsp");
		return;

	}

}
