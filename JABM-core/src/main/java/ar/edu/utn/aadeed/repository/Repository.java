package ar.edu.utn.aadeed.repository;

public interface Repository<T> {

	public boolean add(T object);

	public boolean remove(T object);
	
	public boolean update(T oldObject, T newObject);
	
	public int release();

}