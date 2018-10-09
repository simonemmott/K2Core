package com.k2.core.source;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Component;

public class ComponentGenerator extends CodeGenerator{
	
	public static ComponentGenerator create(File repo, JavaWidgetFactory widgetFactory) {
		return new ComponentGenerator(repo, widgetFactory);
	}

	private ComponentGenerator(File repo, JavaWidgetFactory widgetFactory) {
		super(repo, widgetFactory);
	}
	
	
	public void generate(K2Component component) throws IOException {
		
		Writer writer = component.getSourceFileWriter(getRepository());
		
		if (component instanceof K2Class) {
			K2ClassAssembly assembly = K2ClassAssembly.create(getJavaFactory());
			
			assembly.output(component.getName(), (K2Class)component, writer);
		}
		
		writer.flush();
		writer.close();
		
	}
	
}
