package com.k2.core.assemblies;

import com.k2.JavaAssembly.JavaAssembly;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Domain;
import com.k2.core.model.K2Field;

public class K2SequenceAssembly extends JavaAssembly<K2Domain> {

	public static K2SequenceAssembly create(JavaWidgetFactory javaWidgetFactory) {
		return new K2SequenceAssembly(javaWidgetFactory);
	}

	private K2SequenceAssembly(JavaWidgetFactory javaWidgetFactory) {
		super(javaWidgetFactory.get(K2Domain.class, "K2DomainSequenceWriter"));
	}
}
