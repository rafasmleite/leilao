package br.com.leilao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.leilao.dao.CargoDAO;
import br.com.leilao.domain.Cargo;

@FacesConverter("cargoConverter")
public class CargoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			CargoDAO cargoDAO = new CargoDAO();
			Cargo cargoBuscar = new Cargo();
			cargoBuscar.setId(codigo);
			return cargoDAO.buscarPorID(cargoBuscar);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		try {
			Cargo cargo = (Cargo) objeto;
			return Long.toString(cargo.getId());
		} catch (Exception e) {
			return null;
		}
	}

}
