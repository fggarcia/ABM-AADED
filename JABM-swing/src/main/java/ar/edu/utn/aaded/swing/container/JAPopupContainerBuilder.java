package ar.edu.utn.aaded.swing.container;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewSession;
import ar.edu.utn.aadeed.view.component.JAMember;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.container.builder.JAViewContainerBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.ConvertUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class JAPopupContainerBuilder implements JAViewContainerBuilder {
	
	private String title;
	
	public JAPopupContainerBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public <T> JAViewContainer build(JAViewSession<T> viewSession) {

		JPanel panel = new JPanel(new GridLayout(0, 4), false);
		
		Border margin = new EmptyBorder(10, 10, 10, 80);
		panel.setBorder(margin);
		
		return new JAPopupContainer<T>(title, panel, viewSession);
	}

	private static class JAPopupContainer<T> implements JAViewContainer {

		private JPanel panel;
		
		private String title;
		
		private JAViewSession<T> viewSession;
		
		private List<JAMember> members = Lists.newArrayList();
		
		public JAPopupContainer(String title, JPanel panel, JAViewSession<T> viewSession) {
			this.panel = panel;
			this.title = title;
			this.viewSession = viewSession;
		}

		public void addMember(Object member) {
			JComponent component = JComponent.class.cast(member);
			panel.add(component);
		}

		public void render() {
			throw new NotImplementedException();
		}

		public T renderAndReturn() {
			int result = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (JOptionPane.YES_OPTION == result) {
				JASession<T> session = viewSession.getSession();
				T newInstance = session.createItem(getValuesForMembers());
				return newInstance;
			}

			return null;
		}
		
		public void addMember(JAMember member) {
			JComponent component = JComponent.class.cast(member.getComponent());
			members.add(member);
			panel.add(component);
		}
		
		private Map<String, Object> getValuesForMembers() {
			
			Map<String, Object> values = Maps.newHashMap();
			
			for (JAMember member : members) {
				values.put(member.getField().getName(), ConvertUtils.convert(member.getValue(), member.getField().getClazz()));
			}
			
			return values;
		}
	}
}