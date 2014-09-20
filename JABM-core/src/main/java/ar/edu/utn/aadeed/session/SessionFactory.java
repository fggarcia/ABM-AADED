package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import com.google.common.collect.Maps;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class SessionFactory {

	private static SessionFactory instance = null;

	private final Map<Class, Session> sessions = Maps.newHashMap();

	private SessionRegistrationStrategy sessionStrategy = new SessionRegistrationStrategy();

	private SessionFactory() {
	}

	public synchronized static SessionFactory getInstance() {
		if (instance == null) {
			instance = new SessionFactory();
		}
		return instance;
	}

	public synchronized <T> Session<T> getSession(Class<T> clazz) {

		checkArgument(clazz != null, "clazz cannot be null");
		Session<T> session = (Session<T>) this.sessions.get(clazz);

		if (session == null) {
			session = this.sessionStrategy.buildSession(clazz);
			this.sessions.put(clazz, session);
		}

		return session;
	}
}