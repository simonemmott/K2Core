package com.k2.core;

import java.io.File;
import java.io.IOException;

import com.k2.EntityMap.EntitiesMap;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.common.annotation.MetaDomain;
import com.k2.common.dao.K2Dao;
import com.k2.common.dao.K2DaoFactory;
import com.k2.common.domain.AK2DomainManager;
import com.k2.common.domain.K2DomainManager;
import com.k2.common.reflector.K2Reflector;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Domain;
import com.k2.core.model.K2Entity;
import com.k2.core.source.ComponentGenerator;

@MetaDomain(name="K2Core", description="This is the K2 core development domain")
public class K2CoreDomainManager extends AK2DomainManager implements K2DomainManager {

	public K2CoreDomainManager(K2Domain k2Domain, K2DaoFactory daoFactory) {
		super(k2Domain, daoFactory);
	}

	public void generateSource(String repoPath) throws IOException {

		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
				
		ComponentGenerator gen = ComponentGenerator.create(repo, getJavaFactory());
		
		K2DaoFactory daoFactory = this.getDaoFactory();
		
		K2Dao<K2Entity, Long> k2EntityDao = daoFactory.getDao(K2Entity.class);
		
		
		
		for (K2Entity k2Entity : k2EntityDao.list()) {
			gen.generate(k2Entity);
		}
		
	}

	private JavaWidgetFactory javaFactory;
	public void setJavaFactory(JavaWidgetFactory javaFactory) {
		this.javaFactory = javaFactory;
	}
	public JavaWidgetFactory getJavaFactory() { return javaFactory; }

}
