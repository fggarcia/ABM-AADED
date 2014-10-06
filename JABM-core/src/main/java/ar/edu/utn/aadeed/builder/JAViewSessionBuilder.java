package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;
import ar.edu.utn.aadeed.session.JAFields;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;

public class JAViewSessionBuilder<T> {
	
	private JAViewModule viewModule;
	
	private Class<T> representationFor;
	
	private JAFields fields;

	public JAViewSessionBuilder<T> withViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
		return this;
	}
	
	public JAViewSessionBuilder<T> withFields(JAFields fields) {
		this.fields = fields;
		return this;
	}
	
	public JAViewSessionBuilder<T> withRepresentationFor(Class<T> representationFor) {
		this.representationFor = representationFor;
		return this;
	}
	
	public JAViewSession<T> build() {
		
		checkArgument(viewModule != null, "viewModule cannot be null");
		checkArgument(representationFor != null, "representationFor cannot be null");
		checkArgument(fields != null, "fields cannot be null");
		
		return new JAViewSession<T>(viewModule, representationFor, fields);
	}
}