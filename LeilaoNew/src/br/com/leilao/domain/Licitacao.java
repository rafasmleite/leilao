package br.com.leilao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licitacao")
public class Licitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lic_id")
	private long id;

	@Column(name = "lic_nome", nullable = false, length = 20)
	private String nome;
	
	
	private Date dataDisputa;
	private String horaDisputa;
	private String portalDisputa;
	private String numeroEdital;
	private String uasg;
	private TipoCliente tipoCliente;
	private String orgao;
	private Contato contatoPregoeiro;
	private String cnpj;
	private String ie;
	private Date prazoEntrega;
	private TipoEntrega tipoEntrega;
	private boolean pedidoAmostra;
	private Usuario responsavelCadastramento;
	private Double valorLicitacao;
	private Integer quantidadeItensLicitacao;
	private Usuario responsavelOrcamento;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Licitacao other = (Licitacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
