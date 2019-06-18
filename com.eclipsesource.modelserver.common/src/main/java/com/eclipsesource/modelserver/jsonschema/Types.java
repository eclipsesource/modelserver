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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcorePackage;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Types {

    public static boolean isValid(EClassifier eClassifier) {
        return isBoolean(eClassifier)
                || isString(eClassifier)
                || isNumber(eClassifier)
                || isInteger(eClassifier)
                || isDate(eClassifier)
                || isEnum(eClassifier);
    }

    public  static boolean isBoolean(EClassifier eType) {
        return Boolean.class.isAssignableFrom(eType.getInstanceClass())
                || eType.getInstanceClass() == boolean.class;
    }

    public static boolean isString(EClassifier eType) {
        return String.class.isAssignableFrom(eType.getInstanceClass());
    }

    public static boolean isNumber(EClassifier eClassifier) {
        return isFloat(eClassifier) || isDouble(eClassifier) || isBigDecimal(eClassifier);
    }

    public static boolean isInteger(EClassifier eClassifier) {
        return isBigInteger(eClassifier)
                || isByte(eClassifier)
                || isChar(eClassifier)
                || isInt(eClassifier)
                || isLong(eClassifier)
                || isShort(eClassifier);
    }

    public static boolean isEnum(EClassifier eClassifier) {
        return EcorePackage.eINSTANCE.getEEnum().isInstance(eClassifier);
    }

    public static boolean isDate(EClassifier eClassifier) {
        return Date.class.isAssignableFrom(eClassifier.getInstanceClass())
                || XMLGregorianCalendar.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isFloat(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == float.class
                || Float.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isDouble(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == double.class
                || Double.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isBigDecimal(EClassifier eClassifier) {
        return BigDecimal.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isBigInteger(EClassifier eClassifier) {
        return BigInteger.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isByte(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == byte.class
                || Byte.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isChar(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == char.class
                || Character.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isInt(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == int.class
                || Integer.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isLong(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == long.class
                || Long.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    private static boolean isShort(EClassifier eClassifier) {
        return eClassifier.getInstanceClass() == short.class
                || Short.class.isAssignableFrom(eClassifier.getInstanceClass());
    }

    public static boolean isUnsupportedType(EClassifier eType) {
        return eType == EcorePackage.eINSTANCE.getEByteArray()
                || eType == EcorePackage.eINSTANCE.getEDiagnosticChain()
                || eType == EcorePackage.eINSTANCE.getEEList()
                || eType == EcorePackage.eINSTANCE.getEEnumerator()
                || eType == EcorePackage.eINSTANCE.getEFeatureMap()
                || eType == EcorePackage.eINSTANCE.getEFeatureMapEntry()
                || eType == EcorePackage.eINSTANCE.getEInvocationTargetException()
                || eType == EcorePackage.eINSTANCE.getEJavaClass()
                || eType == EcorePackage.eINSTANCE.getEJavaObject()
                || eType == EcorePackage.eINSTANCE.getEMap()
                || eType == EcorePackage.eINSTANCE.getEResource()
                || eType == EcorePackage.eINSTANCE.getEResourceSet()
                || eType == EcorePackage.eINSTANCE.getETreeIterator();
    }
}
