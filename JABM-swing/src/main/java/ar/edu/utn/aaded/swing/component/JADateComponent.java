package ar.edu.utn.aaded.swing.component;

import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;

import ar.edu.utn.aaded.swing.base.JAComponentUtils;
import ar.edu.utn.aadeed.JADateUtils;
import ar.edu.utn.aadeed.JAReflections;
import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.component.JAViewComponent;
import ar.edu.utn.aadeed.view.component.JAViewType;
import ar.edu.utn.aadeed.view.container.JAContainer;

public class JADateComponent implements JAViewComponent {

	public JAViewType getViewType() {
        return JAViewType.DATE_TIME_PICKER;
    }

	public void renderForSearch(JAFieldDescription field, JAContainer container) {
    	
		JAComponentUtils.addFieldLabel(field, container);
		
		JXDatePicker jCalendar = new JXDatePicker();
		jCalendar.getEditor().setEditable(false);

		container.addMember(JADateMember.getInstanceForSearchAction(field, jCalendar));
    }

	public void renderForAdd(JAFieldDescription field, JAContainer container) {
		
		JAComponentUtils.addFieldLabel(field, container);
		
		JXDatePicker jCalendar = new JXDatePicker();
		jCalendar.getEditor().setEditable(false);

		container.addMember(JADateMember.getInstanceForAddAction(field, jCalendar));
	}

	public void renderForUpdate(Object object, JAFieldDescription field, JAContainer container) {

		JAComponentUtils.addFieldLabel(field, container);
		
		Object itemValue = JAReflections.getFieldValue(object, field.getName());

		JXDatePicker jCalendar = new JXDatePicker();
		jCalendar.setDate(JADateUtils.truncate((Date) itemValue));
		jCalendar.setEnabled(field.isEditable());
		jCalendar.getEditor().setEditable(false);
		
		container.addMember(JADateMember.getInstanceForUpdateAction(field, jCalendar, itemValue));
    }

	private static class JADateMember implements JAMember {
    	
    	private JAFieldDescription field;
    	
    	private JXDatePicker jCalendar;
    	
    	public static JADateMember getInstanceForSearchAction(final JAFieldDescription field, final JXDatePicker jCalendar) {
    		return new JADateMember(field, jCalendar);
    	}
    	
    	public static JADateMember getInstanceForUpdateAction(final JAFieldDescription field, final JXDatePicker jCalendar, final Object originalValue) {
    		
    		final JADateMember dateMember = new JADateMember(field, jCalendar);
    		
    		jCalendar.addFocusListener(JAComponentUtils.createFocusListenerForUpdateAction(field, dateMember, jCalendar, originalValue));
    		
    		return dateMember;
    	}
    	
    	public static JADateMember getInstanceForAddAction(final JAFieldDescription field, final JXDatePicker jCalendar) {
    		
    		final JADateMember dateMember = new JADateMember(field, jCalendar);
    		
    		jCalendar.addFocusListener(JAComponentUtils.createFocusListenerForAddAction(field, dateMember, jCalendar));
    		
    		return dateMember;
    	}
    	
		private JADateMember(JAFieldDescription field, JXDatePicker jCalendar) {
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
			final Date value = jCalendar.getDate();
			if (value != null) {
				return JADateUtils.truncate(jCalendar.getDate());
			}
			return null;
		}
    }
}
