package ar.edu.utn.aadeed.session;

public interface Session<T> {

    public boolean add(T object);

    public boolean remove(T object);

}
