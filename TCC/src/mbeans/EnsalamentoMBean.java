package mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import control.ControleDisciplina;
import control.ControleEnsalamento;
//import control.ControleEscola;
import entidades.Disciplina;
import entidades.Aula;

@ManagedBean(name = "ensalamentoMBean")
@SessionScoped
public class EnsalamentoMBean {

	private ControleEnsalamento modelo;
	private ScheduleModel eventModel;

	public EnsalamentoMBean() {
		eventModel = new DefaultScheduleModel();

		adicionarEvento("teste \n teste2", GregorianCalendar.MONDAY);
//		adicionarAulas();
	}

	// TODO FALAR DANIEL MÉTODO NAO ESTÁ ADICIONANDO EVENTO
	private void adicionarEvento(String descricao,
			int diaSemanaGregorianCalendar) {
		GregorianCalendar calendarioInicio = (GregorianCalendar) GregorianCalendar
				.getInstance();
		calendarioInicio.set(GregorianCalendar.DAY_OF_WEEK,
				diaSemanaGregorianCalendar);
		calendarioInicio.set(GregorianCalendar.HOUR_OF_DAY, 14);
		calendarioInicio.set(GregorianCalendar.MINUTE, 0);

		GregorianCalendar calendarioFim = (GregorianCalendar) GregorianCalendar
				.getInstance();
		calendarioFim.set(GregorianCalendar.DAY_OF_WEEK,
				diaSemanaGregorianCalendar);
		calendarioFim.set(GregorianCalendar.HOUR_OF_DAY, 18);
		calendarioInicio.set(GregorianCalendar.MINUTE, 0);

		eventModel.addEvent(new DefaultScheduleEvent(descricao,
				calendarioInicio.getTime(), calendarioFim.getTime()));
		System.out.println(calendarioInicio.getTime());
		System.out.println(calendarioFim.getTime());
	}

	public Date getInitialDate() {

		adicionarEvento("PWEB - Daniel - TINF", GregorianCalendar.MONDAY);

		GregorianCalendar calendario = (GregorianCalendar) GregorianCalendar
				.getInstance();
		// calendario.set(GregorianCalendar.DAY_OF_WEEK,
		// GregorianCalendar.MONDAY);
		// calendario.set(GregorianCalendar.DAY_OF_MONTH, 18);
		// calendario.set(GregorianCalendar.MONTH, GregorianCalendar.NOVEMBER);
		// calendario.set(GregorianCalendar.YEAR, 2013);
		System.out.println(calendario.getTime());
		return calendario.getTime();
	}

	public void Ensalar() {
		modelo.Ensalar();
	}

	public ArrayList<Aula> getEnsalamento() {
		return modelo.getAulas();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void adicionarAulas() {
		Ensalar();
		ArrayList<Aula> array = getEnsalamento();
		
		for (int i = 0; i < array.size(); i++) {
			String descricao = array.get(i).texto2();
			int diaSemanaGregorianCalendar=0;
			if (array.get(i).getDia().getNome() == "seg") {
				 diaSemanaGregorianCalendar = GregorianCalendar.MONDAY;
			}
			if (array.get(i).getDia().getNome() == "ter") {
				 diaSemanaGregorianCalendar = GregorianCalendar.TUESDAY;
			}
			if (array.get(i).getDia().getNome() == "qua") {
				 diaSemanaGregorianCalendar = GregorianCalendar.WEDNESDAY;
			}
			if (array.get(i).getDia().getNome() == "qui") {
				 diaSemanaGregorianCalendar = GregorianCalendar.THURSDAY;
			}
			if (array.get(i).getDia().getNome() == "sex") {
				 diaSemanaGregorianCalendar = GregorianCalendar.FRIDAY;
			}

			adicionarEvento(descricao, diaSemanaGregorianCalendar);
		}
	}

}