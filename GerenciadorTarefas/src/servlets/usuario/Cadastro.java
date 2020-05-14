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
 * Servlet implementation class Cadastro
 */
@WebServlet(name = "Cadastro.do", urlPatterns = { "/Cadastro.do" })
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(nome)) {
			session.setAttribute("message", "Os campos de nome, e-mail e senha são obrigatórios.");
			response.sendRedirect("cadastro.jsp");
			return;
		}

		if (!v.isValidString(email)) {
			session.setAttribute("message", "Os campos de nome, e-mail e senha são obrigatórios.");
			response.sendRedirect("cadastro.jsp");
			return;
		}

		if (!v.isValidString(senha)) {
			session.setAttribute("message", "Os campos de nome, e-mail e senha são obrigatórios.");
			response.sendRedirect("cadastro.jsp");
			return;
		}

		if (senha.length() < 6) {
			session.setAttribute("message", "Sua senha deve conter ao menos 6 caracteres.");
			response.sendRedirect("cadastro.jsp");
			return;
		}

		UsuarioService usuarioService = new UsuarioService();

		Usuario existe = usuarioService.consultar(email);

		if (existe.isValid()) {

			session.setAttribute("message", "E-mail já cadastrado.");
			response.sendRedirect("cadastro.jsp");
			return;

		} else {

			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setNome(nome);
			usuario.setSenha(senha);

			usuarioService.cadastrar(usuario);

			session.setAttribute("usuario", usuario);
			response.sendRedirect("home.jsp");
			return;

		}

	}

}
