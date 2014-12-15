package ar.edu.utn.aadeed.domain;

import ar.edu.utn.aadeed.annotation.JADescriptor;
import ar.edu.utn.aadeed.annotation.JAEntity;
import ar.edu.utn.aadeed.annotation.JAValidator;
import ar.edu.utn.aadeed.annotation.JAView;
import ar.edu.utn.aadeed.repository.memory.JAMemoryRepositoryFactory;
import ar.edu.utn.aadeed.validator.JAHotelCityValidator;
import com.google.common.base.MoreObjects;

import static ar.edu.utn.aadeed.view.component.JAViewType.TEXT_BOX;

@JAEntity(repositoryFactory = JAMemoryRepositoryFactory.class)
public class City {

    @JAView(order = 1, label = "Identificador", type = TEXT_BOX)
    @JADescriptor(required = true, editable = false, filter = true,
        validators = {
                @JAValidator(validator = JAHotelCityValidator.class)
        })
    private Long id;

    @JAView(order = 1, label = "Descripcion", type = TEXT_BOX)
    @JADescriptor(filter = true, required = true, editable = true, regex = ".{2,18}")
    private String name;

    public City() {
    }

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("name", name).toString();
    }
}
