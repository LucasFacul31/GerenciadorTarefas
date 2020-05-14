
<%
	if (request.getSession().getAttribute("usuario") == null) {
	request.getSession().setAttribute("message", "Usuário não autenticado");
	response.sendRedirect("login.jsp");
	return;
}
%>
<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="service.TarefaService,model.Tarefa,model.Usuario,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="includes/head.html"%>
<title>Home</title>
</head>
<body>
	<%!Usuario usuario;%>
	<%
		usuario = (Usuario) request.getSession().getAttribute("usuario");
	%>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<%!Object message;%>
				<%
					message = request.getSession().getAttribute("message");
				if (message != null) {
					out.print("<div class=\"alert alert-success\" role=\"alert\">" + message + "</div>");
					request.getSession().removeAttribute("message");
				}
				%>
				<h1>TaksManager</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h4>
					Bem-vindo,
					<%=usuario.getNome()%>!<br>Sua lista de tarefas:
				</h4>
			</div>
			<div class="col">
				<a href="excluir-conta.jsp" class="btn btn-danger">Excluir conta</a>
				<a href="editar-conta.jsp" class="btn btn-secondary">Editar
					conta</a> <a href="adicionar-tarefa.jsp" class="btn btn-primary">Adicionar
					tarefa</a>
			</div>
			<div class="col-md-2 text-right">
				<a href="logout.jsp">Sair</a>
			</div>
		</div>
		<div class="row mt-4">
			<%!TarefaService ts = new TarefaService();%>
			<%!ArrayList<Tarefa> lista;%>
			<%
				usuario = (Usuario) request.getSession().getAttribute("usuario");
			lista = ts.listarTarefasUsuario(usuario);

			if (lista.isEmpty()) {
				out.print("<div class=\"col\">");
				out.print("<p>Nenhuma tarefa encontada. <a href=\"adicionar-tarefa.jsp\">Adicione uma tarefa agora mesmo!</a></p>");
				out.print("</div>");
			} else {
				for (Tarefa t : lista) {
					out.print("<div class=\"col-md-4\">");
					out.print("<div class=\"box-tarefa\">");
					out.print("<h2>" + t.getTitulo());
					out.print("<div class=\"action-icons float-right\">");
					out.print("<a href=\"editar-tarefa.jsp?id=" + t.getId() + "\"><i class=\"fas fa-pencil-alt mr-3\"></i></a>");
					out.print("<a href=\"excluir-tarefa.jsp?id=" + t.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a>");
					out.print("</div>");
					out.print("</h2>");
					out.print("<hr>");
					out.print("<p>" + t.getDescricao() + "</h2>");
					out.print("</div>");
					out.print("</div>");
				}
			}
			%>
		</div>
	</div>
</body>
</html>