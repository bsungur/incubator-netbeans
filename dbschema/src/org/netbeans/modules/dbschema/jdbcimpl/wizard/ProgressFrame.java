/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
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
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
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
 */

package org.netbeans.modules.dbschema.jdbcimpl.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

import org.openide.util.NbBundle;

public class ProgressFrame extends javax.swing.JFrame {

    ResourceBundle bundle = NbBundle.getBundle("org.netbeans.modules.dbschema.jdbcimpl.resources.Bundle"); //NOI18N
    public PropertyChangeSupport propertySupport;
    private ProgressHandle progressHandle;
    private JComponent progressComponent;
    private int workunits;
    private boolean finished = false;
    
    /** Creates new form ProgressFrame */
    public ProgressFrame() {
        propertySupport = new PropertyChangeSupport(this);
        
        initComponents ();
        this.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_ProgressFrameTabA11yDesc"));  // NOI18N
        okButton.getAccessibleContext().setAccessibleDescription(bundle.getString("ACS_CancelButtonA11yDesc"));  // NOI18N
        
        progressHandle = ProgressHandleFactory.createHandle(null);
        progressComponent = ProgressHandleFactory.createProgressComponent(progressHandle);
        progressPanel.add(progressComponent);
        progressHandle.start();

        javax.swing.ImageIcon ideIcon = new javax.swing.ImageIcon("/org/netbeans/core/resources/frames/ide.gif"); //NOI18N
        setIconImage(ideIcon.getImage());

        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(380, 150);
        setLocation(dim.width/2 - 190, dim.height/2 - 80);
    }
    
    public void dispose() {
        if (!finished) {
            progressHandle.finish();
            finished = true;
        }
        super.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        progressPanel = new javax.swing.JPanel();
        msgLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle(bundle.getString("Title"));
        setResizable(false);
        progressPanel.setLayout(new java.awt.BorderLayout());

        progressPanel.setMinimumSize(new java.awt.Dimension(20, 20));
        progressPanel.setPreferredSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 11);
        getContentPane().add(progressPanel, gridBagConstraints);

        msgLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        msgLabel.setText(bundle.getString("PreparingToCapture"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 11, 6, 11);
        getContentPane().add(msgLabel, gridBagConstraints);

        okButton.setText(bundle.getString("Close"));
        okButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 11);
        getContentPane().add(okButton, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

  private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    setVisible(false);
    propertySupport.firePropertyChange("cancel", null, Boolean.TRUE); //NOI18N
    
    dispose();    
  }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel msgLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel progressPanel;
    // End of variables declaration//GEN-END:variables

    public void setMaximum(final int max) {
        progressHandle.switchToDeterminate(max);
        workunits = max;
    }
    
    public void setValue(final int value) {
        String message;
        
        progressHandle.progress(value);
        
        if (value >= workunits) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    String message = MessageFormat.format(bundle.getString("Complete"), new String[] {Integer.toString(value)}); //NOI18N
                    msgLabel.setText(message);
                }
            });
        }
    }
    
    public void setMessage(String msg) {
        msgLabel.setText(msg);
    }    
    
    public void finishProgress() {
        // running in the event thread ensures synchronized access to the finished field
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                progressHandle.finish();
                finished = true;
            }
        });
    }
    
    //========== property change support needed for progressbar ==========
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertySupport.addPropertyChangeListener (l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertySupport.removePropertyChangeListener (l);
    }
}