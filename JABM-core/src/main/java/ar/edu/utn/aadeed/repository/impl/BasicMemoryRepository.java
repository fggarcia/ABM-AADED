package ar.edu.utn.aadeed.repository.impl;

import java.util.Set;

import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.Sets;

public class BasicMemoryRepository<T> implements Repository<T> {

	private Set<T> livingObjects = Sets.newConcurrentHashSet();

	public boolean add(T object) {
		return livingObjects.add(object);
	}

	public boolean remove(T object) {
		return livingObjects.remove(object);
	}

	public boolean update(T oldObject, T newObject) {
		return (remove(oldObject)) ? (add(newObject)) : false;
	}

	public int release() {
		int size = livingObjects.size();
		livingObjects.clear();
		return size;
	}
}