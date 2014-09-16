package ar.edu.utn.aadeed.repository;

public interface Repository<T> {

	public boolean add(T object);

	public boolean remove(T object);
	
	public void release();

}