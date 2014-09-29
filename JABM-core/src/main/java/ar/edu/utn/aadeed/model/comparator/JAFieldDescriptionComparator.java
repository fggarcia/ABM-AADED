package ar.edu.utn.aadeed.model.comparator;

import java.util.Comparator;

import ar.edu.utn.aadeed.model.JAFieldDescription;

public class JAFieldDescriptionComparator implements Comparator<JAFieldDescription> {

	public int compare(JAFieldDescription field1, JAFieldDescription field2) {

		int order1 = (field1.hasView()) ? (field1.getView().getOrder()) : -1;
		int order2 = (field2.hasView()) ? (field2.getView().getOrder()) : -1;

		if ((order1 != -1) && (order2 == -1)) {
			return 1;
		}

		if ((order2 != -1) && (order1 == -1)) {
			return -1;
		}

		return field1.getName().compareTo(field2.getName());
	}
}