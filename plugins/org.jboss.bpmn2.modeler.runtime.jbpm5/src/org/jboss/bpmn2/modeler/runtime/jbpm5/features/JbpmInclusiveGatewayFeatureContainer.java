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
 * @author Innar Made
 ******************************************************************************/
package org.jboss.bpmn2.modeler.runtime.jbpm5.features;

import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.modeler.ui.features.gateway.InclusiveGatewayFeatureContainer;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;

public class JbpmInclusiveGatewayFeatureContainer extends InclusiveGatewayFeatureContainer {

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new JbpmCreateInclusiveGatewayFeature(fp);
	}

	public class JbpmCreateInclusiveGatewayFeature extends CreateInclusiveGatewayFeature {

		public JbpmCreateInclusiveGatewayFeature(IFeatureProvider fp) {
			super(fp);
		}

		@Override
		public InclusiveGateway createBusinessObject(ICreateContext context) {
			InclusiveGateway gateway = super.createBusinessObject(context);
			gateway.setName("");
			return gateway;
		}
	}
}