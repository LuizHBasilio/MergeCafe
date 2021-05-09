package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "OPCAO")
public class Opcao {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "S_OPCAO")
	@SequenceGenerator(name = "S_OPCAO", sequenceName = "S_OPCAO", allocationSize = 1)
	private int id;
	@Column(name = "OPCAO", nullable = false)
	private String opcao;

	@ManyToOne
	@JoinColumn(name = "CPF_COLABORADOR", referencedColumnName = "CPF", nullable = false)
	private Colaborador colaborador;

	@Override
	public boolean equals(Object obj) {

		Opcao opcaoEntrada = (Opcao) obj;

		if (this.opcao.equalsIgnoreCase(opcaoEntrada.getOpcao())) {
			return true;
		} else {
			return false;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
