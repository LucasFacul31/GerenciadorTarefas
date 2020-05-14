package service;

import java.util.ArrayList;

import dao.TarefaDAO;
import model.Tarefa;
import model.Usuario;

public class TarefaService {
	private TarefaDAO tarefaDAO;

	public TarefaService() {
		this.tarefaDAO = new TarefaDAO();
	}

	public void cadastrar(Tarefa tarefa) {
		this.tarefaDAO.cadastrar(tarefa);
	}

	public void alterar(Tarefa tarefa) {
		this.tarefaDAO.alterar(tarefa);
	}

	public void excluir(Tarefa tarefa) {
		this.tarefaDAO.excluir(tarefa);
	}

	public Tarefa consultar(int id) {
		return this.tarefaDAO.consultar(id);
	}

	public ArrayList<Tarefa> listarTarefas() {
		return this.tarefaDAO.listarTarefas();
	}

	public ArrayList<Tarefa> listarTarefasUsuario(Usuario usuario) {
		return this.tarefaDAO.listarTarefasUsuario(usuario);
	}
}
