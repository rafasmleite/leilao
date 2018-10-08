package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.Estado;
import br.com.leilao.util.HibernateUtil;

public class EstadoDAO {

	public void salvar(Estado estado) {
		if (estado != null) {
			if (estado.getNome() != null && estado.getNome() != "") {
				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;

				try {
					estado.setNome(estado.getNome().toUpperCase());
					transacao = sessao.beginTransaction();
					sessao.saveOrUpdate(estado);
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

	public List<Estado> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Estado> criteria = sessao.getCriteriaBuilder().createQuery(Estado.class);
			criteria.from(Estado.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<Estado> buscarPorNome(Estado estado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Estado> criteria = sessao.getCriteriaBuilder().createQuery(Estado.class);
			Root<Estado> from = criteria.from(Estado.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), estado.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public Estado buscarPorID(Estado estado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Estado> criteria = sessao.getCriteriaBuilder().createQuery(Estado.class);
			Root<Estado> from = criteria.from(Estado.class);
			criteria.where(sessao.getCriteriaBuilder().equal(from.get("id"), estado.getId()));

			return sessao.createQuery(criteria).getSingleResult();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

}
