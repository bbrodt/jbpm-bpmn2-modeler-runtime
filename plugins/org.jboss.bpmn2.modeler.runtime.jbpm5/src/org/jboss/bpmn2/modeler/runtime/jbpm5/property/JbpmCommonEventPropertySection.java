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

package org.jboss.bpmn2.modeler.runtime.jbpm5.property;

import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.property.events.CommonEventPropertySection;
import org.eclipse.bpmn2.modeler.ui.property.tasks.TaskDetailComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 *
 */
public class JbpmCommonEventPropertySection extends CommonEventPropertySection {
	static {
		PropertiesCompositeFactory.register(Event.class, JbpmCommonEventDetailComposite.class);
	}
	
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new JbpmCommonEventDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new JbpmCommonEventDetailComposite(parent,style);
	}
}
