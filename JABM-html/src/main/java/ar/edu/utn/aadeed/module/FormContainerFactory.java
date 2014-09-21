package ar.edu.utn.aadeed.module;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.PrintWriter;

import ar.edu.utn.aadeed.view.container.ViewContainer;
import ar.edu.utn.aadeed.view.container.ViewContainerFactory;

public class FormContainerFactory implements ViewContainerFactory<String> {

	public ViewContainer<String> newInstance(Object... params) {

		checkParams(params);

		PrintWriter writer = PrintWriter.class.cast(params[0]);

		return new FormContainer(writer);
	}

	private void checkParams(Object... params) {
		checkArgument(params != null, "params cannot be null");
		checkArgument(params.length == 1, "Just need a PrintWriter");
		
		Object param = params[0];
		checkArgument((param != null) && (param.getClass().isAssignableFrom(PrintWriter.class)), "Factory needs a PrintWriter");
	}
}