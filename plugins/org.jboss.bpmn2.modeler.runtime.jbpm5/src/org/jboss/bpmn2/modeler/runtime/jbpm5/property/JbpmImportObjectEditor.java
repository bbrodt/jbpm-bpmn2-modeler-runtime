/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 * All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *******************************************************************************/
package org.jboss.bpmn2.modeler.runtime.jbpm5.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.jboss.bpmn2.modeler.runtime.jbpm5.model.ImportType;
import org.jboss.bpmn2.modeler.runtime.jbpm5.util.JbpmModelUtil;

public class JbpmImportObjectEditor extends TextAndButtonObjectEditor {

	public JbpmImportObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}
	
	@Override
	public Control createControl(Composite composite, String label, int style) {
		super.createControl(composite, label, style);
		// the Text field should be editable
		text.setEditable(true);
		// and change the "Edit" button to a "Browse" to make it clear that
		// an XML type can be selected from the imports 
		defaultButton.setText("Browse Types...");
		return text;
	}

	@Override
	protected void buttonClicked(int buttonId) {
		String name = JbpmModelUtil.showImportDialog(object);
		ImportType imp = JbpmModelUtil.addImport(name, object);
		if (imp!=null)
			setText(imp.getName());
	}
}
