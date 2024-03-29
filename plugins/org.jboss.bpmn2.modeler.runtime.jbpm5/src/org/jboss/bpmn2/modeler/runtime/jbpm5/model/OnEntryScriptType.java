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
package org.jboss.bpmn2.modeler.runtime.jbpm5.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>On Entry Script Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jboss.bpmn2.modeler.runtime.jbpm5.model.OnEntryScriptType#getScript <em>Script</em>}</li>
 *   <li>{@link org.jboss.bpmn2.modeler.runtime.jbpm5.model.OnEntryScriptType#getScriptFormat <em>Script Format</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jboss.bpmn2.modeler.runtime.jbpm5.model.ModelPackage#getOnEntryScriptType()
 * @model extendedMetaData="name='onEntry-script_._type' kind='elementOnly' namespace='##targetNamespace'"
 * @generated
 */
public interface OnEntryScriptType extends EObject {
	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see org.jboss.bpmn2.modeler.runtime.jbpm5.model.ModelPackage#getOnEntryScriptType_Script()
	 * @model required="true"
	 *        extendedMetaData="kind='element' name='script'"
	 * @generated
	 */
	String getScript();

	/**
	 * Sets the value of the '{@link org.jboss.bpmn2.modeler.runtime.jbpm5.model.OnEntryScriptType#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(String value);

	/**
	 * Returns the value of the '<em><b>Script Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Format</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Format</em>' attribute.
	 * @see #setScriptFormat(String)
	 * @see org.jboss.bpmn2.modeler.runtime.jbpm5.model.ModelPackage#getOnEntryScriptType_ScriptFormat()
	 * @model required="true"
	 *        extendedMetaData="kind='attribute' name='scriptFormat'"
	 * @generated
	 */
	String getScriptFormat();

	/**
	 * Sets the value of the '{@link org.jboss.bpmn2.modeler.runtime.jbpm5.model.OnEntryScriptType#getScriptFormat <em>Script Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Format</em>' attribute.
	 * @see #getScriptFormat()
	 * @generated
	 */
	void setScriptFormat(String value);

} // OnEntryScriptType
