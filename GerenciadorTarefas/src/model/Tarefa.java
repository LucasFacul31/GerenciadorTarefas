package model;

public class Tarefa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id = -1;
	private String titulo;
	private String descricao;
	private Usuario usuario;

	public Tarefa() {
	}

	public Tarefa(int id, String titulo, String descricao, Usuario usuario) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
	}
	
	public boolean isValid() {
		return this.id != -1 && this.titulo != null && this.descricao != null && this.usuario != null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}