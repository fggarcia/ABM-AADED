package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.builder.JAViewModuleBuilder;
import ar.edu.utn.aadeed.parser.JASessionParser;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;

import com.google.common.collect.Maps;

public final class JASessionFactory {

	private static JASessionFactory instance = null;

	private final Map<Class<?>, JASession<?>> sessions = Maps.newHashMap();
	
	private volatile JAViewModule viewModule = null;

	private final JASessionParser sessionParser = new JASessionParser();

	private JASessionFactory() { }

	public synchronized static JASessionFactory getInstance() {
		if (instance == null) {
			instance = new JASessionFactory();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized <T> JASession<T> getSession(Class<T> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		JASession<T> session = (JASession<T>) this.sessions.get(clazz);
		return (session == null) ? (createSession(clazz)) : session;
	}

	public JAViewSession getViewSession(Class<?> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		checkArgument(viewModule != null, "Please, register a view module first");
		return new JAViewSession(getSession(clazz), viewModule);
	}

	public synchronized void registerViewModule(JAViewModule viewModule) {
		checkArgument(viewModule != null, "viewModule cannot be null");
		this.viewModule = viewModule;
	}

	public JAViewModuleBuilder getViewModuleBuilder() {
		return new JAViewModuleBuilder(this);
	}

	private <T> JASession<T> createSession(Class<T> clazz) {
		JASession<T> session = this.sessionParser.buildSession(clazz);
		this.sessions.put(clazz, session);
		return session;
	}
}