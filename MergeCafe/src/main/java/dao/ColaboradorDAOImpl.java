package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Colaborador;
import entidade.Opcao;
import util.JpaUtil;

public class ColaboradorDAOImpl implements ColaboradorDAO {

	@Override
	public boolean inserirColaborador(Colaborador colaborador) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		ent.merge(colaborador);

		tx.commit();
		ent.close();

		return true;
	}

	private String montarWhere(Colaborador colaborador, Opcao opcao) {

		String where = "";

		if (colaborador.getCpf() != null && !colaborador.getCpf().isEmpty()) {
			where += " and cl.cpf = '" + colaborador.getCpf() + "'";
		} else {
			if (colaborador.getNome() != null && !colaborador.getNome().isEmpty()) {
				where += " and upper(cl.nome) like '%" + colaborador.getNome().toUpperCase() + "%'";
			}

			if (opcao.getOpcao() != null && !opcao.getOpcao().isEmpty()) {
				where += " and upper(op.opcao) like '%" + opcao.getOpcao().toUpperCase() + "%'";
			}
		}
		return where;
	}

	@Override
	public List<Colaborador> pesquisarColaborador(Colaborador colaborador, Opcao opcao) {
		String sql = "Select distinct cl from Colaborador cl, Opcao op " + " where cl.cpf = op.colaborador.cpf " + montarWhere(colaborador, opcao); 
				
		EntityManager ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery(sql);

		List<Colaborador> listaColaboradores = query.getResultList();

		ent.close();

		return listaColaboradores;
	}

	@Override
	public boolean adicionarOpcaoColaborador(Colaborador colaborador) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		for (Opcao opcao : colaborador.getListaOpcoes()) {
			ent.merge(opcao);
		}

		tx.commit();
		ent.close();

		return true;
	}

	@Override
	public boolean removerOpcaoColaborador(Colaborador colaborador) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		for (Opcao opcao : colaborador.getListaOpcoes()) {
			Opcao existe = ent.find(Opcao.class, opcao.getId());
			if (existe != null) {
				ent.remove(existe);
			}
		}

		tx.commit();
		ent.close();
		return true;
	}

	@Override
	public Colaborador existeColaborador(Colaborador colaborador) {
		EntityManager ent = JpaUtil.getEntityManager();

		Colaborador existe = ent.find(Colaborador.class, colaborador.getCpf());

		ent.close();
		return existe;
	}

	@Override
	public boolean removerOpcaoColaborador(Opcao opcao) {
		EntityManager ent = JpaUtil.getEntityManager();
		Opcao existe = ent.find(Opcao.class, opcao.getId());

		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		ent.remove(existe);

		tx.commit();
		ent.close();
		return true;
	}

	@Override
	public boolean removerColaborador(Colaborador colaborador) {
		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		Colaborador existe = ent.find(Colaborador.class, colaborador.getCpf());

		tx.begin();

		ent.remove(existe);

		tx.commit();
		ent.close();
		return true;
	}

}
