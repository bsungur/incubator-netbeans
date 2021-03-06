/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.bugzilla;

import org.eclipse.mylyn.internal.bugzilla.core.BugzillaRepositoryConnector;
import org.netbeans.modules.mylyn.util.RepositoryConnectorProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author Ondrej Vrabec
 */
@ServiceProviders({
    @ServiceProvider(service = RepositoryConnectorProvider.class),
    @ServiceProvider(service = MylynRepositoryConnectorProvider.class)
})
public final class MylynRepositoryConnectorProvider implements RepositoryConnectorProvider {

    private static MylynRepositoryConnectorProvider INSTANCE;
    
    private final BugzillaRepositoryConnector rc;

    public MylynRepositoryConnectorProvider () {
        rc = new BugzillaRepositoryConnector();
    }

    static MylynRepositoryConnectorProvider getInstance () {
        if (INSTANCE == null) {
            INSTANCE = Lookup.getDefault().lookup(MylynRepositoryConnectorProvider.class);
        }
        return INSTANCE;
    }
    
    @Override
    public BugzillaRepositoryConnector getConnector () {
        return rc;
    }
    
}
