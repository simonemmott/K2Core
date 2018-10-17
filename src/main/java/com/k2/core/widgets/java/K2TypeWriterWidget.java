package com.k2.core.widgets.java;

import java.io.IOException;
import java.io.Writer;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.k2.JavaAssembly.AJavaWidget;
import com.k2.JavaAssembly.JavaAssembly;
import com.k2.Util.classes.Dependency;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetFactory.annotation.Widget;
import com.k2.common.annotation.MetaComponent;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Entity;
import com.k2.core.model.K2Field;
import com.k2.core.model.K2Type;
import com.k2.core.types.ClassType;
import com.k2.core.types.ComponentType;

@Widget("K2TypeWriter")
public class K2TypeWriterWidget extends AJavaWidget<K2Type> {

	public static K2TypeWriterWidget create() {
		return new K2TypeWriterWidget();
	}
	
	public K2TypeWriterWidget() {
		super(K2Type.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,K2Type> node, K2Type data, Writer out, boolean isFirst, boolean isLast) throws IOException {
		
		if (data != null) {			
			
			node.println(out, "@MetaComponent(id={})", data.getId());
						
			node.println(out, "public enum {} {", data.getSimpleName());
			node.println(out);
			node.indent();
			
			node.writeContainer("body", data, out);
			node.outdent();
			node.println(out, "}");
		}
		
		return out;
	}

	@Override
	public void getDependencies(WidgetAssemblyNode<?, K2Type> node, K2Type data) {
		JavaAssembly ja = (JavaAssembly) node.getAssembly();
		
		ja.addDependency(Dependency.forClass(MetaComponent.class));
		

		getEmbeddedDependencies(node, data);
				
		
	}
	

}
