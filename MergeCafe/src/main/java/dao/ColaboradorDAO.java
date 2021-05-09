package dao;

import java.util.List;

import entidade.Colaborador;
import entidade.Opcao;

public interface ColaboradorDAO {
	
	public boolean inserirColaborador(Colaborador colaborador);
	
	public List<Colaborador> pesquisarColaborador(Colaborador colaborador, Opcao opcao);
	
	public boolean adicionarOpcaoColaborador(Colaborador colaborador);
	
	public boolean removerOpcaoColaborador(Colaborador colaborador);
	
	public Colaborador existeColaborador(Colaborador colaborador);

	public boolean removerOpcaoColaborador(Opcao opcao);

	public boolean removerColaborador(Colaborador colaborador);

}
