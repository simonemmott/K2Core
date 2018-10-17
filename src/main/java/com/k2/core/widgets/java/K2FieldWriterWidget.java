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
import com.k2.core.model.K2Field;
import com.k2.core.types.ClassType;
import com.k2.core.types.FieldType;

@Widget("K2FieldWriter")
public class K2FieldWriterWidget extends AJavaWidget<K2Field> {

	public static K2FieldWriterWidget create() {
		return new K2FieldWriterWidget();
	}
	
	public K2FieldWriterWidget() {
		super(K2Field.class);
	}

	@Override
	public Writer output(WidgetAssemblyNode<?,K2Field> node, K2Field data, Writer out, boolean isFirst, boolean isLast) throws IOException {

		if (data.getIsPrimaryKey())
			node.println(out, "@Id");
		node.println(out, "@MetaField(id={}{})", 
				data.getId(),
				(data.getSortOrder()==0)?"":", sortOrder="+data.getSortOrder());
		
		if (data.getColumnName() != null) 
			node.println(out, "@Column(name=\"{}\")", data.getColumnName());

		if (data.getFieldType().equals(FieldType.COLLECTION)) {
			node.println(out, "protected List<{}> {};", ClassUtil.getBasenameFromCanonicalName(data.getDataType().getName()), data.getAlias());

			node.println(out, "public List<{}> {}() { return {}; }",
					data.getDataType().getSimpleName(),
					data.getGetterName(),
					data.getAlias());
			
			node.println(out, "public void {}(List<{}> {}) { this.{} = {}; }",
					data.getSetterName(),
					data.getDataType().getSimpleName(),
					data.getAlias(),
					data.getAlias(),
					data.getAlias());
		} else {
			node.println(out, "protected {} {};", ClassUtil.getBasenameFromCanonicalName(data.getDataType().getName()), data.getAlias());

			node.println(out, "public {} {}() { return {}; }",
					data.getDataType().getSimpleName(),
					data.getGetterName(),
					data.getAlias());
			
			node.println(out, "public void {}({} {}) { this.{} = {}; }",
					data.getSetterName(),
					data.getDataType().getSimpleName(),
					data.getAlias(),
					data.getAlias(),
					data.getAlias());
		}
		

		node.println(out);
		
		return out;
	}

	@Override
	public void getDependencies(WidgetAssemblyNode<?, K2Field> node, K2Field data) {
		JavaAssembly ja = (JavaAssembly) node.getAssembly();
		ja.addDependency(new Dependency(data.getDataType().getName()));

		if (data.getIsPrimaryKey())
			ja.addDependency(Dependency.forClass(Id.class));

		ja.addDependency(Dependency.forClass(MetaField.class));

		if (data.getColumnName() != null)
			ja.addDependency(Dependency.forClass(Column.class));
		
		getEmbeddedDependencies(node, data);
		
		if (data.getFieldType().equals(FieldType.COLLECTION))
			ja.addDependency(Dependency.forClass(List.class));
				
	}
	

}
