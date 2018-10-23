package com.k2.core.assemblies;

import com.k2.JavaAssembly.JavaAssembly;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Field;

public class K2ClassAssembly extends JavaAssembly<K2Class> {

	public static K2ClassAssembly create(JavaWidgetFactory javaWidgetFactory) {
		return new K2ClassAssembly(javaWidgetFactory);
	}

	private K2ClassAssembly(JavaWidgetFactory javaWidgetFactory) {
		super(javaWidgetFactory.get(K2Class.class, "K2ClassWriter"));
		this.root()
				.bind("body", javaWidgetFactory.get(K2Field.class, "K2FieldWriter"), "fields").up();
	}
}
