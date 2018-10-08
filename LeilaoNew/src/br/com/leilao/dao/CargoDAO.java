package br.com.leilao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.leilao.domain.Cargo;
import br.com.leilao.util.HibernateUtil;

public class CargoDAO {

	public void salvar(Cargo cargo) {
		if (cargo != null) {
			if (cargo.getNome() != null && cargo.getNome() != "") {
				Session sessao = HibernateUtil.getSessionFactory().openSession();
				Transaction transacao = null;
				try {
					cargo.setNome(cargo.getNome().toUpperCase());
					transacao = sessao.beginTransaction();
					sessao.saveOrUpdate(cargo);
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

	public List<Cargo> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cargo> criteria = sessao.getCriteriaBuilder().createQuery(Cargo.class);
			criteria.from(Cargo.class);

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}
	}

	public List<Cargo> buscarPorNome(Cargo cargo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cargo> criteria = sessao.getCriteriaBuilder().createQuery(Cargo.class);
			Root<Cargo> from = criteria.from(Cargo.class);
			criteria.where(sessao.getCriteriaBuilder().like(from.get("nome"), cargo.getNome()));

			return sessao.createQuery(criteria).getResultList();

		} catch (RuntimeException e) {
			System.out.println(e);
			return null;
		} finally {
			sessao.close();
		}

	}

	public Cargo buscarPorID(Cargo cargo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();

		try {
			CriteriaQuery<Cargo> criteria = sessao.getCriteriaBuilder().createQuery(Cargo.class);
			Root<Cargo> from = criteria.from(Cargo.class);
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
