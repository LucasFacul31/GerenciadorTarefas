package servlets.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.Validation;
import model.Usuario;
import service.UsuarioService;

/**
 * Servlet implementation class DeleteUsuario
 */
@WebServlet(name = "DeleteUsuario.do", urlPatterns = { "/DeleteUsuario.do" })
public class DeleteUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario") == null) {
			request.getSession().setAttribute("message", "Usuário não autenticado");
			response.sendRedirect("login.jsp");
			return;
		}
		

		String confirm = request.getParameter("confirm");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(confirm)) {
			session.setAttribute("message", "Erro ao enviar o formulário.");
			response.sendRedirect("excluir-usuario.jsp");
			return;
		}

		UsuarioService usuarioService = new UsuarioService();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		usuarioService.excluir(usuario);

		session.setAttribute("message", "Conta excluída com sucesso!");
		session.invalidate();
		response.sendRedirect("login.jsp");
		return;
		
	}

}
