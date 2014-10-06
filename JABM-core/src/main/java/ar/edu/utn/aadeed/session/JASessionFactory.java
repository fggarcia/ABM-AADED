package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.builder.JAViewModuleBuilder;
import ar.edu.utn.aadeed.builder.JAViewSessionBuilder;
import ar.edu.utn.aadeed.parser.JASessionParser;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;

import com.google.common.collect.Maps;

public final class JASessionFactory {
	
	private static final Object lock = new Object();

	private volatile static JASessionFactory instance = null;

	private final Map<Class<?>, JASession<?>> sessions = Maps.newConcurrentMap();
	
	private volatile JAViewModule viewModule = null;

	private final JASessionParser sessionParser = new JASessionParser();

	private JASessionFactory() { }

	public static JASessionFactory getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new JASessionFactory();		
				}
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> JASession<T> getSession(Class<T> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		JASession<T> session = (JASession<T>) this.sessions.get(clazz);
		return (session == null) ? (createSession(clazz)) : session;
	}

	public <T> JAViewSession<T> getViewSession(Class<T> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		checkArgument(viewModule != null, "Please, register a view module first");
		JAViewSessionBuilder<T> builder = getSession(clazz).getViewSessionBuilder();
		return builder.withViewModule(viewModule).build();
	}

	public void registerViewModule(JAViewModule viewModule) {
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