package br.com.leilao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.leilao.dao.EmpresaDAO;
import br.com.leilao.domain.Empresa;

@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			EmpresaDAO empresaDAO = new EmpresaDAO();
			Empresa empresaBuscar = new Empresa();
			empresaBuscar.setId(codigo);
			return empresaDAO.buscarPorID(empresaBuscar);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try {
			Empresa estado = (Empresa) objeto;
			return Long.toString(estado.getId());
		} catch (Exception e) {
			return null;
		}
	}

}
