
<%
	if (request.getSession().getAttribute("usuario") == null) {
	request.getSession().setAttribute("message", "Usuário não autenticado");
	response.sendRedirect("login.jsp");
	return;
}
if (request.getParameter("id") == null) {
	request.getSession().setAttribute("message", "Tarefa não encontrada");
	response.sendRedirect("home.jsp");
	return;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="service.TarefaService,model.Tarefa"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="includes/head.html"%>
<title>Excluir tarefa</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="DeleteTarefa.do" method="post" class="form-default">
					<%!Object message;%>
					<%!int id;%>
					<%
						message = request.getSession().getAttribute("message");
					if (message != null) {
						out.print("<div class=\"alert alert-danger\" role=\"alert\">" + message + "</div>");
						request.getSession().removeAttribute("message");
					}

					id = Integer.parseInt(request.getParameter("id"));
					Tarefa t;
					TarefaService ts = new TarefaService();
					t = ts.consultar(id);
					%>
					<input type="hidden" name="id" value="<%=id%>" />
					<h1>Excluir tarefa</h1>
					<p>
						Tem certeza que deseja excluir a tarefa <b><%=t.getTitulo()%></b>?
					</p>
					<a href="home.jsp" class="btn btn-secondary">Voltar</a>
					<button type="submit" class="btn btn-danger">Excluir</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>