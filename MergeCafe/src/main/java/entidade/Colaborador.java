package entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "COLABORADOR")
public class Colaborador {

	@Id
	@Column(name = "cpf", nullable = false)
	private String cpf;
	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Opcao> listaOpcoes;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Opcao> getListaOpcoes() {
		return listaOpcoes;
	}

	public void setListaOpcoes(List<Opcao> listaOpcoes) {
		this.listaOpcoes = listaOpcoes;
	}

	@Override
	public boolean equals(Object obj) {

		Colaborador colaboradorEntrada = (Colaborador) obj;

		if (this.cpf.equals(colaboradorEntrada.getCpf())) {
			return true;
		} else {
			return false;
		}

	}
}
