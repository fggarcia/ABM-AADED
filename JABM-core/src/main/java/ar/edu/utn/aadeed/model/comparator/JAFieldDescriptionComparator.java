package ar.edu.utn.aadeed.model.comparator;

import java.util.Comparator;

import ar.edu.utn.aadeed.model.JAFieldDescription;

public class JAFieldDescriptionComparator implements Comparator<JAFieldDescription> {

	public int compare(JAFieldDescription field1, JAFieldDescription field2) {

		Integer order1 = (field1.hasView()) ? (field1.getView().getOrder()) : -1;
		Integer order2 = (field2.hasView()) ? (field2.getView().getOrder()) : -1;

		if ((order1 >= 0) && (order2 < 0)) {
			return 1;
		}

		if ((order2 >= -1) && (order1 < -1)) {
			return -1;
		}
		
		if ((order1 >= 0) && (order2 >= 0)) {
			return order1.compareTo(order2);
		}

		return field1.getName().compareTo(field2.getName());
	}
}