package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Tarefa;
import model.Usuario;

import java.sql.ResultSet;

public class TarefaDAO {

	private Connection conexao;

	public TarefaDAO() {
		this.conexao = ConnectionFactory.conectar();
	}

	public void cadastrar(Tarefa tarefa) {

		String inserir = "INSERT INTO tarefas (titulo, descricao, fk_usuario_email) VALUES (?, ?, ?);";

		try (PreparedStatement pst = conexao.prepareStatement(inserir)) {

			pst.setString(1, tarefa.getTitulo());
			pst.setString(2, tarefa.getDescricao());
			pst.setString(3, tarefa.getUsuario().getEmail());

			//pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();

		}
	}

	public void alterar(Tarefa tarefa) {

		String alterar = "UPDATE tarefas SET titulo = ?, descricao = ?, fk_usuario_email = ? WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(alterar)) {

			pst.setString(1, tarefa.getTitulo());
			pst.setString(2, tarefa.getDescricao());
			pst.setString(3, tarefa.getUsuario().getEmail());
			pst.setInt(4, tarefa.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();

		}
	}

	public void excluir(Tarefa tarefa) {

		String excluir = "DELETE FROM tarefas WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(excluir)) {

			pst.setInt(1, tarefa.getId());

			pst.execute();

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();
		}
	}

	public Tarefa consultar(int id) {

		String consultar = "SELECT * FROM tarefas WHERE id = ?;";

		try (PreparedStatement pst = conexao.prepareStatement(consultar)) {

			pst.setInt(1, id);
			ResultSet resultado = pst.executeQuery();

			Tarefa t = new Tarefa();
			if (resultado.next()) {
			
				t.setId(id);
				t.setTitulo(resultado.getString("titulo"));
				t.setDescricao(resultado.getString("descricao"));
				t.setUsuario(null);
				
			}
			return t;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();

			return null;
		}
	}

	public ArrayList<Tarefa> listarTarefas() {

		String consultar = "SELECT * FROM tarefas";

		try (PreparedStatement pst = conexao.prepareStatement(consultar)) {

			ResultSet resultado = pst.executeQuery();

			ArrayList<Tarefa> lista = new ArrayList<>();
			while (resultado.next()) {
				Tarefa t = new Tarefa();
				
				t.setId(resultado.getInt("id"));
				t.setTitulo(resultado.getString("titulo"));
				t.setDescricao(resultado.getString("descricao"));
				t.setUsuario(null);
				
				lista.add(t);
			}
			return lista;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();

			return null;
		}
	}

	public ArrayList<Tarefa> listarTarefasUsuario(Usuario usuario) {

		String consultar = "SELECT * FROM tarefas AS t INNER JOIN usuario AS u ON t.fk_usuario_email = u.email WHERE u.email = ?";

		try (PreparedStatement pst = conexao.prepareStatement(consultar)) {

			pst.setString(1, usuario.getEmail());
			ResultSet resultado = pst.executeQuery();

			ArrayList<Tarefa> lista = new ArrayList<>();
			while (resultado.next()) {
				Tarefa t = new Tarefa();
				Usuario u = new Usuario();
				
				u.setEmail(resultado.getString("email"));
				u.setSenha(resultado.getString("senha"));
				u.setNome(resultado.getString("nome"));
				
				t.setId(resultado.getInt("id"));
				t.setTitulo(resultado.getString("titulo"));
				t.setDescricao(resultado.getString("descricao"));
				t.setUsuario(u);
				
				lista.add(t);
			}
			return lista;

		} catch (SQLException ex) {

			System.err.println("Não foi possível manipular a tabela Tarefas.");
			ex.printStackTrace();

			return null;
		}
	}

}