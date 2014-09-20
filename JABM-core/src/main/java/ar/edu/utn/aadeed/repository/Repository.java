package ar.edu.utn.aadeed.repository;

public interface Repository<T> {

	public boolean add(T newObject);

	public boolean remove(T oldObject);
	
	public boolean update(T oldObject, T newObject);
	
	public int release();

}