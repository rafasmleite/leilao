package br.com.leilao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.leilao.dao.EstadoDAO;
import br.com.leilao.domain.Estado;

@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estadoBuscar = new Estado();
			estadoBuscar.setId(codigo);
			return estadoDAO.buscarPorID(estadoBuscar);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try {
			Estado estado = (Estado) objeto;
			return estado.getId().toString();
		} catch (Exception e) {
			return null;
		}
	}

}
