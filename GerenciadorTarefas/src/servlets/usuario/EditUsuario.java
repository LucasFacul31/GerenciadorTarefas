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
 * Servlet implementation class EditUsuario
 */
@WebServlet(name = "EditUsuario.do", urlPatterns = { "/EditUsuario.do" })
public class EditUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario") == null) {
			request.getSession().setAttribute("message", "Usuário não autenticado");
			response.sendRedirect("login.jsp");
			return;
		}

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String old_senha = request.getParameter("old_senha");
		String new_senha = request.getParameter("new_senha");
		String new_senha_confirm = request.getParameter("new_senha_confirm");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(nome)) {
			session.setAttribute("message", "Por favor, preencha todos os campos obrigatórios.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}

		if (!v.isValidString(old_senha)) {
			session.setAttribute("message", "Por favor, preencha todos os campos obrigatórios.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}

		if (!v.isValidString(new_senha)) {
			session.setAttribute("message", "Por favor, preencha todos os campos obrigatórios.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}

		if (!v.isValidString(new_senha_confirm)) {
			session.setAttribute("message", "Por favor, preencha todos os campos obrigatórios.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}

		if (new_senha.length() < 6) {
			session.setAttribute("message", "Sua senha deve conter ao menos 6 caracteres.");
			response.sendRedirect("cadastro.jsp");
			return;
		}

		UsuarioService usuarioService = new UsuarioService();

		Usuario actual_usuario = (Usuario) session.getAttribute("usuario");
		Usuario new_usuario = new Usuario();

		String actual_email = actual_usuario.getEmail();
		String actual_senha = actual_usuario.getSenha();

		if (!actual_email.equalsIgnoreCase(email)) {
			session.setAttribute("message", "E-mail e/ou senha incorretos.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}

		if (!actual_senha.equalsIgnoreCase(old_senha)) {
			session.setAttribute("message", "E-mail e/ou senha incorretos.");
			response.sendRedirect("editar-conta.jsp");
			return;
		}
		
		new_usuario.setEmail(email);
		new_usuario.setNome(nome);
		new_usuario.setSenha(new_senha);

		usuarioService.alterar(new_usuario);

		session.setAttribute("usuario", new_usuario);
		session.setAttribute("message", "Conta atualizada com sucesso!");
		response.sendRedirect("home.jsp");
		return;

	}

}
