package com.k2.core.assemblies;

import com.k2.JavaAssembly.JavaAssembly;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Field;
import com.k2.common.model.K2Type;
import com.k2.common.model.K2TypeValue;

public class K2TypeAssembly extends JavaAssembly<K2Type> {

	public static K2TypeAssembly create(JavaWidgetFactory javaWidgetFactory) {
		return new K2TypeAssembly(javaWidgetFactory);
	}

	private K2TypeAssembly(JavaWidgetFactory javaWidgetFactory) {
		super(javaWidgetFactory.get(K2Type.class, "K2TypeWriter"));
		this.root()
				.bind("body", javaWidgetFactory.get(K2TypeValue.class, "K2TypeValueWriter"), "values").up();
	}
}
