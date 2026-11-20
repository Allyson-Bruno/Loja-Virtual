package ti.validator;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("comecaComMaiuscula")
public class ComecaComMaiuscula implements Validator<Object> {

	public void validate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com letra maiúscula"));
		}
	}
}