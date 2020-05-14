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
 * Servlet implementation class Login
 */
@WebServlet(name = "Login.do", urlPatterns = { "/Login.do" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		Validation v = new Validation();
		HttpSession session = request.getSession();

		if (!v.isValidString(email)) {
			session.setAttribute("message", "Os campos de e-mail e senha são obrigatórios.");
			response.sendRedirect("login.jsp");
			return;
		}

		if (!v.isValidString(senha)) {
			session.setAttribute("message", "Os campos de e-mail e senha são obrigatórios.");
			response.sendRedirect("login.jsp");
			return;
		}

		UsuarioService usuarioService = new UsuarioService();
		Usuario resultado = usuarioService.logar(email, senha);

		if (resultado == null || !resultado.isValid()) {
			session.setAttribute("message", "E-mail e/ou senha incorretos.");
			System.out.println(resultado);
			response.sendRedirect("login.jsp");
			return;
		} else {
			session.setAttribute("usuario", resultado);
			response.sendRedirect("home.jsp");
			return;
		}
	}

}
