package ar.edu.utn.aadeed.repository.impl;

import java.util.List;

import ar.edu.utn.aadeed.repository.Repository;

import com.google.common.collect.Lists;

public class BasicMemoryRepository<T> implements Repository<T> {

	private List<T> livingObjects = Lists.newArrayList();

	public boolean add(T object) {
		return livingObjects.add(object);
	}

	public boolean remove(T object) {
		return livingObjects.remove(object);
	}

	public void release() {
		livingObjects.clear();
	}
}
