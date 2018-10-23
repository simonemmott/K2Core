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
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Entity;
import com.k2.common.model.K2Field;
import com.k2.common.types.ClassType;
import com.k2.common.types.ComponentType;

@Widget("K2ClassWriter")
public class K2ClassWriterWidget extends AJavaWidget<K2Class> {

	public static K2ClassWriterWidget create() {
		return new K2ClassWriterWidget();
	}
	
	public K2ClassWriterWidget() {
		super(K2Class.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,K2Class> node, K2Class data, Writer out, boolean isFirst, boolean isLast) throws IOException {
				
		if (data != null) {
			K2Class extendsClass = data.getExtendsClass();
			K2Class extendsEntity = extendsClass;
			while (extendsEntity != null) {
				extendsEntity = extendsEntity.getExtendsClass();
				if (extendsEntity instanceof K2Entity)
					break;
			}
			
			
			node.println(out, "@MetaComponent(id={})", data.getId());
			
			if (data instanceof K2Entity)
				node.println(out, "@Entity");
			
			if (extendsClass != null) {
				if (data.getDiscriminatorValue() != null)
					node.println(out, "@DiscriminatorValue(\"{}\")", data.getDiscriminatorValue().getAlias());
			}
			
			K2Field discriminatorField = data.getDiscriminatorField();
			if (discriminatorField != null) {
				node.println(out, "@Inheritance(strategy=InheritanceType.JOINED)");
				node.println(out, "@DiscriminatorColumn(name=\"{}\", discriminatorType = DiscriminatorType.STRING)", discriminatorField.getColumnName());
			}
			
			node.println(out, "public class {}{}{} {", 
					data.getSimpleName(), 
					(extendsClass==null)?"":" extends "+extendsClass.getSimpleName(),
					"");
			node.println(out);
			node.indent();
			
			if (discriminatorField != null) {
				node.println(out, "public {}(Long id, {} {}) {",
						data.getSimpleName(),
						discriminatorField.getDataType().getSimpleName(),
						discriminatorField.getAlias());
				node.indent();
				if (data.getDiscriminatorValue() != null)
					node.println(out, "super(id, {}.{});",
							data.getDiscriminatorValue().getDefiningType().getSimpleName(),
							data.getDiscriminatorValue().getAlias());
				if (extendsEntity == null) 
					node.println(out, "this.id = id;");
				node.println(out, "this.{} = {};",
						discriminatorField.getAlias(),
						discriminatorField.getAlias());
				node.outdent();
				node.println(out, "}");
			} else {
				node.println(out, "public {}(Long id) {",data.getSimpleName());
				if (data.getDiscriminatorValue() != null)
					node.println(out, "super(id, {}.{});",
							data.getDiscriminatorValue().getDefiningType().getSimpleName(),
							data.getDiscriminatorValue().getAlias());
				node.indent();
					node.println(out, "this.id = id;");
					node.outdent();
					node.println(out, "}");
				
			}
			node.println(out);

			node.writeContainer("body", data, out);
			node.outdent();
			node.println(out, "}");
		}
		
		return out;
	}

	@Override
	public void getDependencies(WidgetAssemblyNode<?, K2Class> node, K2Class data) {
		JavaAssembly ja = (JavaAssembly) node.getAssembly();
		K2Class extendsClass = data.getExtendsClass();
		K2Class extendsEntity = extendsClass;
		while (extendsEntity != null && extendsEntity instanceof K2Entity)
			extendsEntity = extendsEntity.getExtendsClass();
		
		ja.addDependency(Dependency.forClass(MetaComponent.class));
		
		if (data.getExtendsClass() != null)
			ja.addDependency(new Dependency(data.getExtendsClass().getName()));
		
		if (data instanceof K2Entity)
			ja.addDependency(Dependency.forClass(Entity.class));
		
		K2Field discriminatorField = data.getDiscriminatorField();
		
		if (extendsEntity != null) {
			ja.addDependency(Dependency.forClass(Inheritance.class));
			ja.addDependency(Dependency.forClass(InheritanceType.class));
			ja.addDependency(Dependency.forClass(DiscriminatorValue.class));
			if (data.getDiscriminatorValue() != null)
				ja.addDependency(Dependency.fromString(data.getDiscriminatorValue().getDefiningType().getName()));
			
		}

		if (discriminatorField != null) {
			ja.addDependency(Dependency.forClass(DiscriminatorColumn.class));
			ja.addDependency(Dependency.forClass(DiscriminatorType.class));
		}

		getEmbeddedDependencies(node, data);
				
		
	}
	

}
