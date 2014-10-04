package ar.edu.utn.aadeed.builder;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import ar.edu.utn.aadeed.model.JAFieldDescription;
import ar.edu.utn.aadeed.model.comparator.JAFieldDescriptionComparator;
import ar.edu.utn.aadeed.session.JASession;
import ar.edu.utn.aadeed.view.JAViewModule;
import ar.edu.utn.aadeed.view.JAViewSession;

public class JAViewSessionBuilder<T> {
	
	private JAViewModule viewModule;

	private List<JAFieldDescription> fields;
	
	private JASession<T> session;

	public JAViewSessionBuilder<T> withViewModule(JAViewModule viewModule) {
		this.viewModule = viewModule;
		return this;
	}
	
	public JAViewSessionBuilder<T> withSession(JASession<T> session) {
		this.session = session;
		return this;
	}

	public JAViewSessionBuilder<T> withFields(List<JAFieldDescription> fields) {
		this.fields = fields;
		return this;
	}

	public JAViewSession<T> build() {

		checkArgument(viewModule != null, "viewModule cannot be null");
		checkArgument(fields != null, "fields cannot be null");

		return new JAViewSession<T>(filterAndOrder(fields), viewModule, session);
	}
	
	private List<JAFieldDescription> filterAndOrder(List<JAFieldDescription> fields) {
		
		List<JAFieldDescription> fieldsToShow = Lists.newArrayList(getFieldsToShow(fields));
		
		Collections.sort(fieldsToShow, new JAFieldDescriptionComparator());
		
		return fieldsToShow;
	}
	
	private Iterable<JAFieldDescription> getFieldsToShow(List<JAFieldDescription> fields) {
		return Iterables.filter(fields, new Predicate<JAFieldDescription>() {
			public boolean apply(JAFieldDescription input) {
				return input.hasView();
			}
		});
	}
}