/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.bpmn2.modeler.runtime.jbpm5.drools.process.core.impl;

import org.jboss.bpmn2.modeler.runtime.jbpm5.drools.process.core.WorkDefinitionExtension;

public class WorkDefinitionExtensionImpl extends WorkDefinitionImpl implements WorkDefinitionExtension {
    
    private static final long serialVersionUID = 510l;
    
    private String displayName;
    private String explanationText;
    private String icon;
    private String editor;
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExplanationText() {
        return explanationText;
    }
    
    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCustomEditor() {
        return editor;
    }
    
    public void setCustomEditor(String editor) {
        this.editor = editor;
    }
    
}
