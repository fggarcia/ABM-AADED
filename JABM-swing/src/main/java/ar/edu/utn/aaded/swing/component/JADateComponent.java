package ar.edu.utn.aaded.swing.component;

import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class JADateComponent implements JAViewComponent {

	public JAViewType getViewType() {
        return JAViewType.DATE_TIME_PICKER;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);

		JXDatePicker jCalendar = new JXDatePicker();

		container.addMember(new JADateMember(field, jCalendar));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {
		this.renderForSearch(field, container);
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

        JLabel fieldLabel = new JLabel(field.getLabel() + ":", JLabel.RIGHT);
        container.addMember(fieldLabel);

		Object itemValue = JAReflections.getFieldValue(object, field.getName());

		JXDatePicker jCalendar = new JXDatePicker();
		jCalendar.setDate(((Date) itemValue));
		
		container.addMember(new JADateMember(field, jCalendar));
    }

    public static class JADateMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JXDatePicker jCalendar;
    	
		public JADateMember(JAFieldDescription field, JXDatePicker jCalendar) {
			this.field = field;
			this.jCalendar = jCalendar;
		}

		public JAFieldDescription getField() {
			return field;
		}

		public Object getComponent() {
			return jCalendar;
		}

		public Object getValue() {
			return jCalendar.getDate();
		}
    }
}
