
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
<title>Editar tarefa</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<form action="EditTarefa.do" method="post" class="form-default">
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
					<h1>Editar tarefa</h1>
					<div class="form-group">
						<label for="titulo">Título</label> <input type="text"
							class="form-control" id="titulo"
							placeholder="Digite o título da tarefa" name="titulo"
							value="<%=t.getTitulo()%>" required>
					</div>
					<div class="form-group">
						<label for="descricao">Descrição</label>
						<textarea class="form-control" id="descricao"
							placeholder="Digite a descrição da tarefa" name="descricao" required><%=t.getDescricao()%></textarea>
					</div>
					<a href="home.jsp" class="btn btn-secondary">Voltar</a>
					<button type="submit" class="btn btn-primary">Atualizar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>