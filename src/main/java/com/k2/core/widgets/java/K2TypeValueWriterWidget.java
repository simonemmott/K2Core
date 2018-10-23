package com.k2.core.widgets.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import com.k2.JavaAssembly.AJavaWidget;
import com.k2.JavaAssembly.JavaAssembly;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependency;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetFactory.annotation.Widget;
import com.k2.common.annotation.MetaComponent;
import com.k2.common.annotation.MetaField;
import com.k2.common.annotation.MetaTypeValue;
import com.k2.common.model.K2Field;
import com.k2.common.model.K2TypeValue;
import com.k2.common.types.ClassType;
import com.k2.common.types.FieldType;

@Widget("K2TypeValueWriter")
public class K2TypeValueWriterWidget extends AJavaWidget<K2TypeValue> {

	public static K2TypeValueWriterWidget create() {
		return new K2TypeValueWriterWidget();
	}
	
	public K2TypeValueWriterWidget() {
		super(K2TypeValue.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,K2TypeValue> node, K2TypeValue data, Writer out, boolean isFirst, boolean isLast) throws IOException {

		node.println(out, "@MetaTypeValue(id={}, name=\"{}\", description=\"{}\")",
				data.getId(),
				data.getName(),
				data.getDescription());
		node.println(out, "{}{}", data.getAlias(),(isLast)?"":",");
		
		return out;
	}

	@Override
	public void getDependencies(WidgetAssemblyNode<?, K2TypeValue> node, K2TypeValue data) {
		JavaAssembly ja = (JavaAssembly) node.getAssembly();

		ja.addDependency(Dependency.forClass(MetaTypeValue.class));
				
	}
	

}
