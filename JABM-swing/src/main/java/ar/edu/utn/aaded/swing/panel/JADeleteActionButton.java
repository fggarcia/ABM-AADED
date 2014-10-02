package ar.edu.utn.aaded.swing.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ar.edu.utn.aadeed.session.JASessionFactory;
import ar.edu.utn.aadeed.view.component.JAViewActionButton;
import ar.edu.utn.aadeed.view.container.JAViewContainer;
import ar.edu.utn.aadeed.view.table.JAViewRecordTable;

public class JADeleteActionButton implements JAViewActionButton {

	public <T> void render(JAViewContainer container, final JAViewRecordTable<T> table) {
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				T item = table.getSelectedItem();
				Class<T> clazz = (Class<T>) item.getClass();
				JASessionFactory.getInstance().getSession(clazz).remove(item);
				table.refresh(JASessionFactory.getInstance().getSession(clazz).getFiltersBuilder().search());
			}
		});
		container.addMember(delete);
	}
}