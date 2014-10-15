package ar.edu.utn.aadeed.view.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;

public class JAViewSessionBuilder<T> {
	
	private JAViewModule viewModule;
	
	private JASession<T> session;
	
	public JAViewSessionBuilder<T> withViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
		return this;
	}
	
	public JAViewSessionBuilder<T> withSession(JASession<T> session) {
		this.session = session;
		return this;
	}
	
	public JAViewSession<T> build() {
		
		checkArgument(viewModule != null, "viewModule cannot be null");
		checkArgument(session != null, "session cannot be null");
		
		return new JAViewSession<T>(viewModule, session);
	}
}