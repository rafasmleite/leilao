package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.Cidade;
import br.com.leilao.util.HibernateUtil;

public class CidadeDAO {

	public void salvar(Cidade cidade) {
		if (cidade != null) {
			if (cidade.getNome() != null && cidade.getNome() != "") {
				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;

				try {
					cidade.setNome(cidade.getNome().toUpperCase());
					transacao = sessao.beginTransaction();
					sessao.saveOrUpdate(cidade);
					transacao.commit();
				} catch (RuntimeException e) {
					System.out.println(e);
					if (transacao != null) {
						transacao.rollback();
					}
				} finally {
					sessao.close();
				}
			} else {

			}
		} else {

		}
	}

	public List<Cidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cidade> criteria = sessao.getCriteriaBuilder().createQuery(Cidade.class);
			criteria.from(Cidade.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<Cidade> buscarPorNome(Cidade cargo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cidade> criteria = sessao.getCriteriaBuilder().createQuery(Cidade.class);
			Root<Cidade> from = criteria.from(Cidade.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), cargo.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public Cidade buscarPorID(Cidade cargo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cidade> criteria = sessao.getCriteriaBuilder().createQuery(Cidade.class);
			Root<Cidade> from = criteria.from(Cidade.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), cargo.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}
	
}
