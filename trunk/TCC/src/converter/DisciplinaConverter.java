package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entidades.Disciplina;

/*
 * Pronúncia: DisciplinaConvérter (Ingreis amigus <3)
 * 
 * 
 * ESTAS CLASSES DE CONVERSÃO SERVEM APENAS PARA PODER TRANSFORMAR OBJETOS EM STRINGS E VICE-VERSA.
 * É OBRIGATÓRIO PARA EXIBIR NOS CHECKBOXES VALORES EM STRING (como nome da Disciplina),
 *  QUANDO ESSA STRING NA VERDADE REPRESENTA UM OBJETO DISCIPLINA.
 * 
 * 
 * 
 */


@FacesConverter(forClass=Disciplina.class)
public class DisciplinaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
    	
    	if (submittedValue == null || submittedValue.isEmpty()){
    		return null;
    	}
    	
    	try {
//            return userService.find(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
        }
    	
    	return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
    	
    	if (modelValue == null){
    		return "";
    	}
    	
    	if (modelValue instanceof Disciplina){
    		return String.valueOf(((Disciplina) modelValue).getId());
    	}else{
    		throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", modelValue)));
    	}
    }

}