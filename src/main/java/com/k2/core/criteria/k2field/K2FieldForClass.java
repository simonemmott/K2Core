package com.k2.core.criteria.k2field;

import java.util.List;


import com.google.common.collect.Lists;
import com.k2.common.criteria.AbstractK2ListCriteria;
import com.k2.common.criteria.K2Criteria;
import com.k2.common.criteria.K2ListCriteria;
import com.k2.common.criteria.K2ListCriteriaBuilder;
import com.k2.common.dao.FieldInitialiser;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Field;

@K2Criteria(forClass=K2Field.class, alias="forClass")
public class K2FieldForClass extends AbstractK2ListCriteria<K2Field> implements K2ListCriteria<K2Field> {
	
	public K2FieldForClass(K2ListCriteriaBuilder lcb) {
		super(lcb);
		
		rootNode = lcb.root(K2Field.class, this)
				.eq(lcb.field("k2Class"), lcb.parameter(K2Class.class, "k2Class"))
				.root();
		
	}
	
	@Override
	public FieldInitialiser<K2Field>[] getInitialisers() {
		List<FieldInitialiser<K2Field>> inits = Lists.newArrayList();
		
		inits.add(FieldInitialiser.fromParameter(K2Field.class, "k2Class", "k2Class"));
		
		return inits.toArray(new FieldInitialiser[inits.size()]);
	}

}