package ar.edu.utn.aadeed.validator;

import ar.edu.utn.aadeed.domain.Hotel;
import ar.edu.utn.aadeed.exception.JAException;
import ar.edu.utn.aadeed.session.JASessionFactory;

import java.util.List;

public class JAHotelCityValidator extends JAOperationValidator{
    public void validateOnDelete(Object value) throws JAException {
        List<Hotel> hotels = JASessionFactory.getInstance()
                .getSession(Hotel.class).getFiltersBuilder().add("cityId", value).search();

        if(!hotels.isEmpty()) {
            throw new JAException("Exist hotel with this city");
        }
    }
}
