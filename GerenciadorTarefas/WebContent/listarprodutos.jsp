<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
	import="service.ProdutoService, model.Produto, java.util.ArrayList, java.text.NumberFormat, java.util.Locale"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de produtos</title>
</head>
<body>
	<table cellpadding="10" border="1">
		<tr>
			<th>Código</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Valor</th>
			<th>Estoque</th>
		</tr>
			<%
				ProdutoService ps = new ProdutoService();
			ArrayList<Produto> lista;
			lista = ps.listarProdutos();
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
			for (Produto p : lista) {
				out.print("<tr>");
				out.print("<td>" + p.getCodigo() + "</td>");
				out.print("<td>" + p.getNome() + "</td>");
				out.print("<td>" + p.getDescricao() + "</td>");
				out.print("<td>" + numberFormat.format(p.getValor()) + "</td>");
				out.print("<td>" + p.getEstoque() + "</td>");
				out.print("</tr>");
			}
			%>
	</table>
</body>
</html>