package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ColaboradorDAO;
import dao.ColaboradorDAOImpl;
import entidade.Colaborador;
import entidade.Opcao;

@ManagedBean(name = "ColaboradorBean")
@SessionScoped
public class ColaboradorBean {

	private Colaborador colaborador;
	private Opcao opcao;
	private List<Colaborador> listaColaboradores;

	private ColaboradorDAO colaboradorDAO;

	// Valores utilizados para regra de remover as opções
	private String opcaoSel;

	// Atributo utilizado para limpar campos da tela de pesquisa
	private boolean origemPesquisa = false;

	// Atributo utilizado para saber se está em modo de edição ou inclusão
	private boolean modoEdicao = false;

	// Valores para pesquisar e remover
	private String cpfSelecionado;

	public ColaboradorBean() {
		this.colaboradorDAO = new ColaboradorDAOImpl();
		this.inicializarCampos();
	}

	public void inicializarCampos() {
		if (!origemPesquisa) { // Vai entrar aqui quando vier a primeira vez da pesquisa
			this.colaborador = new Colaborador();
			this.colaborador.setListaOpcoes(new ArrayList<Opcao>());
			modoEdicao = false;
		}

		origemPesquisa = false;

		// this.contato é o objeto referenciado para adicionar os contatos em tela
		this.opcao = new Opcao();

		this.listaColaboradores = new ArrayList<Colaborador>();
	}

	public void salvar() {

		if (!modoEdicao) {
			if (colaboradorDAO.existeColaborador(colaborador) == null) {
				this.colaboradorDAO.inserirColaborador(colaborador);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, colaborador.getNome(), " inserido com sucesso"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Colaborador já existe!"));
			}
		} else {
			this.colaboradorDAO.inserirColaborador(colaborador);
			this.colaborador = this.colaboradorDAO.existeColaborador(colaborador);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Colaborador alterado com sucesso"));
		}
	}

	public void adicionarOpcao() {

		opcao.setColaborador(colaborador);
		this.colaborador.getListaOpcoes().add(opcao);

		// Criar nova instância para não afetar outra inserção.
		this.opcao = new Opcao();
	}

	public void removerOpcao() {

		Opcao achou = null;
		for (Opcao cont : colaborador.getListaOpcoes()) {
			if (opcaoSel != null && !opcaoSel.isEmpty()) {

				if (cont.getOpcao().equals(opcaoSel)) {
					achou = cont;
				}

			} else if (opcaoSel != null && !opcaoSel.isEmpty()) {
				if (cont.getOpcao().equals(opcaoSel)) {
					achou = cont;
				}

			}
		}

		if (achou != null) {
			if (achou.getId() > 0) {
				this.colaboradorDAO.removerOpcaoColaborador(achou);
			}

			colaborador.getListaOpcoes().remove(achou);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Opção removida"));
		}

	}

	public void pesquisarColaborador() {
		this.listaColaboradores = colaboradorDAO.pesquisarColaborador(colaborador, opcao);
	}

	public String editarColaborador() {

		this.origemPesquisa = true;
		this.modoEdicao = true;

		Colaborador colaboradorPesquisa = new Colaborador();
		colaboradorPesquisa.setCpf(cpfSelecionado);

		this.colaborador = this.colaboradorDAO.existeColaborador(colaboradorPesquisa);

		return "/cadastroColaborador.xhtml?faces-redirect=true&amp;includeViewParams=true";
	}

	public void removerColaborador() {

		Colaborador colaboradorPesquisa = new Colaborador();
		colaboradorPesquisa.setCpf(cpfSelecionado);

		this.colaboradorDAO.removerColaborador(colaboradorPesquisa);

		this.pesquisarColaborador();
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

	public List<Colaborador> getListaColaboradores() {
		return listaColaboradores;
	}

	public void setListaColaboradores(List<Colaborador> listaColaboradores) {
		this.listaColaboradores = listaColaboradores;
	}

	public ColaboradorDAO getColaboradorDAO() {
		return colaboradorDAO;
	}

	public void setColaboradorDAO(ColaboradorDAO colaboradorDAO) {
		this.colaboradorDAO = colaboradorDAO;
	}

	public String getOpcaoSel() {
		return opcaoSel;
	}

	public void setOpcaoSel(String opcaoSel) {
		this.opcaoSel = opcaoSel;
	}

	public boolean isOrigemPesquisa() {
		return origemPesquisa;
	}

	public void setOrigemPesquisa(boolean origemPesquisa) {
		this.origemPesquisa = origemPesquisa;
	}

	public boolean isModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

	public String getCpfSelecionado() {
		return cpfSelecionado;
	}

	public void setCpfSelecionado(String cpfSelecionado) {
		this.cpfSelecionado = cpfSelecionado;
	}

}
