package com.k2.core.source;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.JavaAssembly.AJavaWidget;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Component;
import com.k2.common.model.K2Domain;
import com.k2.common.model.K2Entity;
import com.k2.common.model.K2Type;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.assemblies.K2SequenceAssembly;
import com.k2.core.assemblies.K2TypeAssembly;

public class ComponentGenerator extends CodeGenerator{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static ComponentGenerator create(File repo, JavaWidgetFactory widgetFactory) {
		return new ComponentGenerator(repo, widgetFactory);
	}

	private ComponentGenerator(File repo, JavaWidgetFactory widgetFactory) {
		super(repo, widgetFactory);
	}
	
	
	public void generate(K2Component component) throws IOException {
		
		
		if (	component instanceof K2Entity ||
				component instanceof K2Type) {

			logger.trace("Generating source for component: {} with class {}", component.getSimpleName(), component.getClass().getSimpleName());

			Writer writer = component.getSourceFileWriter(getRepository());

			if (component instanceof K2Entity) {
				
				K2ClassAssembly assembly = K2ClassAssembly.create(getJavaFactory());
				
				assembly.output(component.getName(), (K2Class)component, writer);
			} else if (component instanceof K2Type) {
				K2TypeAssembly assembly = K2TypeAssembly.create(getJavaFactory());
				
				assembly.output(component.getName(), (K2Type)component, writer);
			}
		
			writer.flush();
			writer.close();
			
		}
		
		
	}
	
	public void generate(K2Domain domain) throws IOException {
		
		
		logger.trace("Generating source for domain: {} sequences {}", domain.getName(), domain.getSequencesClassName());
		
		K2SequenceAssembly assembly = K2SequenceAssembly.create(getJavaFactory());

		Writer writer = domain.getSequenceFileWriter(getRepository());

		assembly.output(domain.getSequencesClassName(), domain, writer);
		
	
		writer.flush();
		writer.close();
			
		
		
	}
	
}
