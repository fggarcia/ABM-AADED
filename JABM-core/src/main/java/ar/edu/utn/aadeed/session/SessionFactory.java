package ar.edu.utn.aadeed.session;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import ar.edu.utn.aadeed.builder.ViewModuleBuilder;
import ar.edu.utn.aadeed.parser.SessionParser;
import ar.edu.utn.aadeed.view.ViewModule;
import ar.edu.utn.aadeed.view.ViewSession;

import com.google.common.collect.Maps;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class SessionFactory {

	private static SessionFactory instance = null;

	private final Map<Class, Session> sessions = Maps.newHashMap();
	
	private volatile ViewModule viewModule = null;

	private final SessionParser sessionParser = new SessionParser();

	private SessionFactory() { }

	public synchronized static SessionFactory getInstance() {
		if (instance == null) {
			instance = new SessionFactory();
		}
		return instance;
	}

	public synchronized <T> ViewSession<T> getViewSession(Class<T> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		checkArgument(viewModule != null, "Please, register a view module first");
		return new ViewSession<T>(getSession(clazz), viewModule);
	}
	
	public synchronized void registerViewModule(ViewModule viewModule) {
		checkArgument(viewModule != null, "viewModule cannot be null");
		this.viewModule = viewModule;
	}
	
	public ViewModuleBuilder getViewModuleBuilder() {
		return new ViewModuleBuilder(this);
	}
	
	private <T> Session<T> getSession(Class<T> clazz) {
		checkArgument(clazz != null, "clazz cannot be null");
		Session<T> session = (Session<T>) this.sessions.get(clazz);
		return (session == null) ? (createSession(clazz)) : session;
	}
	
	private <T> Session<T> createSession(Class<T> clazz) {
		Session<T> session = this.sessionParser.buildSession(clazz);
		this.sessions.put(clazz, session);
		return session;
	}
}