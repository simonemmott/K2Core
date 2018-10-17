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
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetFactory.annotation.Widget;
import com.k2.common.annotation.MetaComponent;
import com.k2.common.annotation.MetaSequences;
import com.k2.common.sequence.K2Sequence;
import com.k2.common.sequence.K2SequenceFactory;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Component;
import com.k2.core.model.K2Domain;
import com.k2.core.model.K2Entity;
import com.k2.core.model.K2Field;
import com.k2.core.types.ClassType;
import com.k2.core.types.ComponentType;

@Widget("K2DomainSequenceWriter")
public class K2SequenceWriterWidget extends AJavaWidget<K2Domain> {

	public static K2SequenceWriterWidget create() {
		return new K2SequenceWriterWidget();
	}
	
	public K2SequenceWriterWidget() {
		super(K2Domain.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,K2Domain> node, K2Domain data, Writer out, boolean isFirst, boolean isLast) throws IOException {
				
		if (data != null) {
			
			String domainNameClass = StringUtil.classCase(data.getName());
			node.println(out, "@MetaSequences");
			node.println(out, "public class {}Sequences extends K2SequenceFactory {", domainNameClass);
			node.println(out);
			node.indent();
			
			node.println(out, "public {}Sequences() {", domainNameClass);
			node.indent();
			
			for (Class<?> cls : data.getDomainManager().getDaoFactory().getManagedEntities()) {
				
				K2Sequence<?> seq = data.getDomainManager().getDaoFactory().getSequence(cls);
				node.println(out, "this.initialise({}.class, {});", cls.getSimpleName(), seq.currentValue());
				
			}
			
			node.outdent();
			node.println(out, "}");

			node.outdent();
			node.println(out, "}");
		}
		
		return out;
	}

	@Override
	public void getDependencies(WidgetAssemblyNode<?, K2Domain> node, K2Domain data) {
		JavaAssembly ja = (JavaAssembly) node.getAssembly();
		
		ja.addDependency(Dependency.forClass(MetaSequences.class));
		ja.addDependency(Dependency.forClass(K2SequenceFactory.class));
		
		for (Class<?> cls : data.getDomainManager().getDaoFactory().getManagedEntities()) {
			ja.addDependency(Dependency.forClass(cls));
		}
		
	}
	

}
