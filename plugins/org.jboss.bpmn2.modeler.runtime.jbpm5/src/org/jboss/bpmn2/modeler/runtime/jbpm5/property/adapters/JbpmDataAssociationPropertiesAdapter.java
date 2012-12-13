package org.jboss.bpmn2.modeler.runtime.jbpm5.property.adapters;

import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataAssociation;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.properties.DataAssociationPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.jboss.bpmn2.modeler.runtime.jbpm5.model.GlobalType;

public class JbpmDataAssociationPropertiesAdapter extends
		DataAssociationPropertiesAdapter {

	public JbpmDataAssociationPropertiesAdapter(AdapterFactory adapterFactory,
			DataAssociation object) {
		super(adapterFactory, object);

    	EStructuralFeature ref;
    	
    	ref = Bpmn2Package.eINSTANCE.getDataAssociation_SourceRef();
    	setFeatureDescriptor(ref, new JbpmSourceTargetFeatureDescriptor(adapterFactory,object,ref) {
    		
    	});

		ref = Bpmn2Package.eINSTANCE.getDataAssociation_TargetRef();
    	setFeatureDescriptor(ref, new JbpmSourceTargetFeatureDescriptor(adapterFactory,object,ref) {
    		
    	});
	}

	public class JbpmSourceTargetFeatureDescriptor extends SourceTargetFeatureDescriptor {

		public JbpmSourceTargetFeatureDescriptor(AdapterFactory adapterFactory,
				DataAssociation object, EStructuralFeature feature) {
			super(adapterFactory, object, feature);
		}

		@Override
		public Hashtable<String, Object> getChoiceOfValues(Object context) {
			EObject object = adopt(context);
			Hashtable<String, Object> choices = super.getChoiceOfValues(context);
			EObject container = ModelUtil.getContainer(object);
			Definitions definitions = ModelUtil.getDefinitions(container);
			for (RootElement re : definitions.getRootElements()) {
				if (re instanceof Process) {
					Process process = (Process)re;
					for (GlobalType g : ModelUtil.getAllExtensionAttributeValues(process, GlobalType.class)) {
						choices.put(g.getIdentifier(), g);
					}
				}
			}
			return choices;
		}
	}
	
}
