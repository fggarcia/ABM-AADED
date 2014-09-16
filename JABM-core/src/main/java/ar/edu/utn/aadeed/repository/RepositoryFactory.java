package ar.edu.utn.aadeed.repository;

public interface RepositoryFactory {

	public <T> Repository<T> newInstance(Class<T> clazz);

}
