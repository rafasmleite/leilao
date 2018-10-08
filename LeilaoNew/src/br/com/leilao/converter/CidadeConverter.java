package br.com.leilao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.leilao.dao.CidadeDAO;
import br.com.leilao.domain.Cidade;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			CidadeDAO cidadeDAO = new CidadeDAO();
			Cidade cidadeBuscar = new Cidade();
			cidadeBuscar.setId(codigo);
			return cidadeDAO.buscarPorID(cidadeBuscar);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try {
			Cidade cidade = (Cidade) objeto;
			return Long.toString(cidade.getId());
		} catch (Exception e) {
			return null;
		}
	}

}
