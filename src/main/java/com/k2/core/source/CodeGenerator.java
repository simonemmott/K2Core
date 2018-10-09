package com.k2.core.source;

import java.io.File;

import com.k2.JavaAssembly.JavaWidgetFactory;

public class CodeGenerator {
	
	public static CodeGenerator create(File repo, JavaWidgetFactory widgetFactory) {
		return new CodeGenerator(repo, widgetFactory);
	}

	private final File repo;
	public File getRepository() { return repo; }
	
	private final JavaWidgetFactory widgetFactory;
	public JavaWidgetFactory getJavaFactory() { return widgetFactory; }
	
	protected CodeGenerator(File repo, JavaWidgetFactory widgetFactory) {
		this.repo = repo;
		this.widgetFactory = widgetFactory;
	}
	
	
}
