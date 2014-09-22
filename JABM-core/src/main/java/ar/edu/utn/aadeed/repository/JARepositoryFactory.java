package ar.edu.utn.aadeed.repository;

public interface JARepositoryFactory {

	public <T> JARepository<T> newInstance(Class<T> clazz);

}
