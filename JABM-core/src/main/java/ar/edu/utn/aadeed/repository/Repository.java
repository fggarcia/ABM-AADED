package ar.edu.utn.aadeed.repository;

import java.util.List;

import ar.edu.utn.aadeed.model.Filter;

public interface Repository<T> {

	public boolean add(T newObject);

	public boolean remove(T oldObject);
	
	public boolean update(T oldObject, T newObject);
	
	public List<T> search(List<Filter> filters);
	
	public int release();

}