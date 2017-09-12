/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package org.netbeans.modules.maven.model.settings.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.netbeans.modules.maven.model.settings.Properties;
import org.netbeans.modules.maven.model.settings.SettingsComponent;
import org.netbeans.modules.maven.model.settings.SettingsComponentVisitor;
import org.netbeans.modules.maven.model.settings.SettingsModel;
import org.netbeans.modules.maven.model.settings.SettingsQName;
import org.w3c.dom.Element;

/**
 *
 * @author mkleint
 */
public class PropertiesImpl extends SettingsComponentImpl implements Properties {

    public PropertiesImpl(SettingsModel model, Element element) {
        super(model, element);
    }
    
    public PropertiesImpl(SettingsModel model) {
        this(model, createElementNS(model, model.getSettingsQNames().PROPERTIES));
    }

    // attributes

    // child elements


    @Override
    public void setProperty(String key, String value) {
        QName qname = SettingsQName.createQName(key, getModel().getSettingsQNames().getNamespaceVersion());
        setChildElementText(qname.getLocalPart(), value,
                qname);
    }

    @Override
    public String getProperty(String key) {
        return getChildElementText(SettingsQName.createQName(key, getModel().getSettingsQNames().getNamespaceVersion()));
    }

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> toRet = new HashMap<String, String>();
        List<SettingsComponent> chlds = getChildren();
        for (SettingsComponent pc : chlds) {
            Element el = pc.getPeer();
            String key = el.getLocalName();
            String val = getChildElementText(SettingsQName.createQName(key, getModel().getSettingsQNames().getNamespaceVersion()));
            toRet.put(key, val);
        }
        return toRet;
    }

    @Override
    public void accept(SettingsComponentVisitor visitor) {
        visitor.visit(this);
    }

}