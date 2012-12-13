/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.jboss.bpmn2.modeler.runtime.jbpm5.property.adapters;

import java.util.Hashtable;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.FormalExpressionPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Bob Brodt
 *
 */
public class JbpmFormalExpressionPropertiesAdapter extends FormalExpressionPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public JbpmFormalExpressionPropertiesAdapter(AdapterFactory adapterFactory, FormalExpression object) {
		super(adapterFactory, object);

    	final EStructuralFeature language = Bpmn2Package.eINSTANCE.getFormalExpression_Language();
    	FeatureDescriptor<FormalExpression> fd = super.getFeatureDescriptor(language);
    	
    	Hashtable<String, Object> choiceOfValues = fd.getChoiceOfValues(object);
		if (!(object.eContainer() instanceof SequenceFlow))
			choiceOfValues.remove("Rule");
    	fd.setChoiceOfValues(choiceOfValues);
	}

}
