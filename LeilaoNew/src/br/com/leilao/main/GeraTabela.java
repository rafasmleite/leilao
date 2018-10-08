package br.com.leilao.main;

import br.com.leilao.util.HibernateUtil;

public class GeraTabela {
	
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();
	}

}
