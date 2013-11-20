package mbeans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import control.ControleDisciplina;
import entidades.Disciplina;

@ManagedBean(name = "ensalamentoMBean")
@SessionScoped
public class EnsalamentoMBean {


	private ScheduleModel eventModel;  
  
    public EnsalamentoMBean() {  
        eventModel = new DefaultScheduleModel();  
//        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));  
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));  
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));  
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));  
//        Date comeco = new Date()
//        new DefaultScheduleEvent("Teste", dataComeco, dateFim)
    } 

}