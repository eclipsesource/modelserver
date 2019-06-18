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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Test;

import java.util.Collections;

import static com.eclipsesource.modelserver.jsonschema.Json.prop;
import static org.junit.Assert.assertEquals;

public class JsonSchemaTest {

    private final String ECLASS_NAME = "TestEClass";
    private final String REFERENCE_NAME = "testReference";
    private final String ATTRIBUTE_NAME = "testAttribute";

    @Test
    public void createJsonSchema_FromEClassWithOptionalSingleReference() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(ECLASS_NAME);

        EClass refClass = EcoreFactory.eINSTANCE.createEClass();
        refClass.setName(ECLASS_NAME + "2");

        EReference eReference = EcoreTestUtil.eReference("testReference", -1, 1, refClass);
        eClass.getEStructuralFeatures().add(eReference);

        final JsonNode actual = JsonSchema.from(eClass);
        final ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties",
                Json.object(
                    prop(REFERENCE_NAME,
                        Json.object(
                            prop("type", Json.text("object")),
                            prop("properties", Json.object()),
                            prop("additionalProperties", Json.bool(false))
                        )
                    )
                )
            ),
            prop("additionalProperties", Json.bool(false))

        );

        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithMandatorySingleReference() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(ECLASS_NAME);

        EClass refClass = EcoreFactory.eINSTANCE.createEClass();
        refClass.setName(ECLASS_NAME + "2");

        EReference eReference = EcoreTestUtil.eReference("testReference", 1, 1, refClass);
        eClass.getEStructuralFeatures().add(eReference);

        final JsonNode actual = JsonSchema.from(eClass);
        final ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties",
                Json.object(
                    prop(REFERENCE_NAME, Json.object(
                        prop("type", Json.text("object")),
                        prop("properties", Json.object()),
                        prop("additionalProperties", Json.bool(false))
                    ))
                )
            ),
            prop("additionalProperties", Json.bool(false)),
            prop("required", Json.array(Collections.singletonList(REFERENCE_NAME)))
        );

        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithOptionalMultiReference() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(ECLASS_NAME);

        EClass refClass = EcoreFactory.eINSTANCE.createEClass();
        refClass.setName(ECLASS_NAME + "2");

        EReference eReference = EcoreTestUtil.eReference(REFERENCE_NAME, -1, 10, refClass);
        eClass.getEStructuralFeatures().add(eReference);

        final JsonNode actual = JsonSchema.from(eClass);

        final ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object(
                prop(REFERENCE_NAME, Json.object(
                    prop("type", Json.text("array")),
                    prop("items", Json.object(
                        prop("type", Json.text("object")),
                        prop("properties", Json.object()),
                        prop("additionalProperties", Json.bool(false))
                    ))
                ))

            )),
            prop("additionalProperties", Json.bool(false))
        );

        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithMandatoryMultiReference() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(ECLASS_NAME);

        EClass refClass = EcoreFactory.eINSTANCE.createEClass();
        refClass.setName(ECLASS_NAME + "2");

        EReference eReference = EcoreTestUtil.eReference(REFERENCE_NAME, 1, 10, refClass);
        eClass.getEStructuralFeatures().add(eReference);

        final JsonNode actual = JsonSchema.from(eClass);

        ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object(
                prop(REFERENCE_NAME, Json.object(
                    prop("type", Json.text("array")),
                    prop("items", Json.object(
                        prop("type", Json.text("object")),
                        prop("properties", Json.object()),
                        prop("additionalProperties", Json.bool(false))
                    ))
                ))
            )),
            prop("additionalProperties", Json.bool(false)),
            prop("required", Json.array(Collections.singletonList(REFERENCE_NAME)))

        );

        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithCircularReference() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(ECLASS_NAME);

        EClass refClass = EcoreFactory.eINSTANCE.createEClass();
        refClass.setName(ECLASS_NAME + "2");

        EReference eReference = EcoreTestUtil.eReference(REFERENCE_NAME, -1, 1, refClass);
        eClass.getEStructuralFeatures().add(eReference);

        EReference eReference2 = EcoreTestUtil.eReference(REFERENCE_NAME, -1, 1, eClass);
        refClass.getEStructuralFeatures().add(eReference2);

        final JsonNode actual = JsonSchema.from(eClass);

        ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object(

                prop(REFERENCE_NAME, Json.object(

                    prop("type", Json.text("object")),
                    prop("properties", Json.object()),
                    prop("additionalProperties", Json.bool(false))

                ))

            )),
            prop("additionalProperties", Json.bool(false))
        );

        // eReference2 should not exist in output
        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEmptyEClass() {
        EClass eClass = EcoreTestUtil.emptyEClass(ECLASS_NAME);
        final JsonNode actual = JsonSchema.from(eClass);
        ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object()),
            prop("additionalProperties", Json.bool(false))
        );
        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithOptionalStringAttribute() {
        EClass eClass = EcoreTestUtil.emptyEClass(ECLASS_NAME);
        EAttribute optionalStringEAttribute = EcoreTestUtil.stringEAttribute(ATTRIBUTE_NAME, 0, 1);
        eClass.getEStructuralFeatures().add(optionalStringEAttribute);

        final JsonNode actual = JsonSchema.from(eClass);
        final ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object(
                prop(ATTRIBUTE_NAME, Json.object(
                    prop("type", Json.text("string"))
                ))
            )),
            prop("additionalProperties", Json.bool(false))
        );
        assertEquals(expected, actual);
    }

    @Test
    public void createJsonSchema_FromEClassWithMandatoryStringAttribute() {
        EClass eClass = EcoreTestUtil.emptyEClass(ECLASS_NAME);
        EAttribute mandatoryStringEAttribute = EcoreTestUtil.stringEAttribute(ATTRIBUTE_NAME,1, 1);
        eClass.getEStructuralFeatures().add(mandatoryStringEAttribute);

        final JsonNode actual = JsonSchema.from(eClass);
        final ObjectNode expected = Json.object(
            prop("type", Json.text("object")),
            prop("properties", Json.object(
                prop(ATTRIBUTE_NAME, Json.object(prop("type", Json.text("string"))))
            )),
            prop("additionalProperties", Json.bool(false)),
            prop("required", Json.array(Collections.singletonList(ATTRIBUTE_NAME)))
        );
        assertEquals(expected, actual);
    }
}
