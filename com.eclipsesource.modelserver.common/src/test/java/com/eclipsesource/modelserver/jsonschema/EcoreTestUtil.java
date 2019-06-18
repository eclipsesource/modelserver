/*******************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License v. 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0.
 *
 *   This Source Code may also be made available under the following Secondary
 *   Licenses when the conditions for such availability set forth in the Eclipse
 *   Public License v. 2.0 are satisfied: GNU General Public License, version 2
 *   with the GNU Classpath Exception which is available at
 *   https://www.gnu.org/software/classpath/license.html.
 *
 *   SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 *******************************************************************************/
package com.eclipsesource.modelserver.jsonschema;

import org.eclipse.emf.ecore.*;

public class EcoreTestUtil {

    public static EReference eReference(String name, int lower, int upper, EClassifier type) {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName(name);
        eReference.setLowerBound(lower);
        eReference.setUpperBound(upper);
        eReference.setEType(type);
        return eReference;
    }

    public static EClass emptyEClass(String name) {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(name);
        return eClass;
    }

    public static EAttribute stringEAttribute(String name, int lower, int upper) {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName(name);
        eAttribute.setLowerBound(lower);
        eAttribute.setUpperBound(upper);
        eAttribute.setEType(EcorePackage.eINSTANCE.getEString());
        return eAttribute;
    }

}
