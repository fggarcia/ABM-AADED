package ar.edu.utn.aadeed.validator;

import ar.edu.utn.aadeed.domain.City;
import ar.edu.utn.aadeed.exception.JAException;
import ar.edu.utn.aadeed.session.JASessionFactory;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import java.util.List;

public class JACityValidator extends JAOperationValidator {

	@Override
	public void validateOnUpdate(final Object oldValue, final Object newValue) throws JAException {
		checkCity(newValue);
	}

	@Override
	public void validateOnAdd(final Object value) throws JAException{
		checkCity(value);
	}

	private void checkCity(final Object newValue) throws JAException {
		List<City> citiesList = JASessionFactory.getInstance().getSession(City.class)
				.getFiltersBuilder().search();

		final boolean any = Iterables.any(citiesList, new Predicate<City>() {
			@Override
			public boolean apply(City city) {
				return city.getId().equals(newValue);
			}
		});

		if (!any)
			throw new JAException("City not found");
	}
}