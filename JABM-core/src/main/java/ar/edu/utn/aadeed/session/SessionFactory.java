package ar.edu.utn.aadeed.session;

import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public final class SessionFactory {

	private static SessionFactory instance = null;

	private final Map<Class<?>, Session> sessions = Maps.newConcurrentMap();

	private SessionFactory() {
	}

	public synchronized static SessionFactory getInstance() {
		if (instance == null) {
			instance = new SessionFactory();
		}
		return instance;
	}

	public <T> Optional<Session> findSession(Class<T> clazz) {
		return Optional.fromNullable(sessions.get(clazz));
	}
}