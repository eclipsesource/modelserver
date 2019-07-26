/*******************************************************************************
 * Copyright (c) 2019 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 *******************************************************************************/
package com.eclipsesource.modelserver.emf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.eclipsesource.modelserver.common.codecs.EMFJsonConverter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class EMFJsonConverterTest extends AbstractResourceTest {

	private EMFJsonConverter emfJsonConverter;

	private String simpleTest_json;
	private EClass simpleTest_eClass;
	private String coffee_json;
	private Resource coffee_resource;

	@BeforeClass
	public static void beforeClass() {
		BasicConfigurator.configure();
	}

	@Before
	public void before() throws IOException {
		this.emfJsonConverter = new EMFJsonConverter();
		simpleTest_json = "{\n" + "  \"eClass\" : \"http://www.eclipse.org/emf/2002/Ecore#//EClass\",\n"
				+ "  \"name\" : \"SimpleTest\"\n" + "}";

		simpleTest_eClass = EcoreFactory.eINSTANCE.createEClass();
		simpleTest_eClass.setName("SimpleTest");
		coffee_json = FileUtils.readFileToString(new File(RESOURCE_PATH + "/" + "Coffee.json"), "UTF8");

		coffee_resource = loadResource("Coffee.ecore");
	}

	@Test
	public void testToJson_simple() {
		String expected = simpleTest_json;
		Optional<String> result = emfJsonConverter.toJson(simpleTest_eClass);
		assertTrue(result.isPresent());
		assertEquals(result.get(), expected);
	}

	@Test
	public void testFromJson_simple() {
		EObject expected = simpleTest_eClass;
		Optional<EObject> result = emfJsonConverter.fromJson(simpleTest_json);
		assertTrue(result.isPresent());
		assertTrue(EcoreUtil.equals(expected, result.get()));
	}

	@Test
	public void testFromJson_explicitCast() {
		Optional<EPackage> result = emfJsonConverter.fromJson(coffee_json, EPackage.class);
		assertTrue(result.isPresent());
		assertTrue(EPackage.class.isInstance(result.get()));

	}

	@Test
	public void testFromJson_failedCast() {
		Optional<EEnumLiteral> result = emfJsonConverter.fromJson(coffee_json, EEnumLiteral.class);
		assertFalse(result.isPresent());

	}

	@Test
	public void testToJson_coffee() {
		String expected = coffee_json;
		Optional<String> result = emfJsonConverter.toJson(coffee_resource.getContents().get(0));
		assertTrue(result.isPresent());
		assertEquals(result.get(), expected);

	}

	@Test
	public void testFromJson_coffee() {
		EObject expected = coffee_resource.getContents().get(0);
		Optional<EObject> result = emfJsonConverter.fromJson(coffee_json);
		assertTrue(result.isPresent());
		assertTrue(quickIsEqual(expected, result.get()));

	}

	private boolean quickIsEqual(EObject pack1, EObject pack2) {
		if (!pack1.getClass().equals(pack2.getClass())) {
			return false;
		}

		if (pack1 instanceof ENamedElement) {
			if (!((ENamedElement) pack1).getName().equals(((ENamedElement) pack2).getName())) {
				return false;
			}

		}

		if (pack1.eContents().size() != pack2.eContents().size()) {
			return false;
		}

		return true;
	}

	@Test
	public void simple() {
		Optional<EObject> result = emfJsonConverter.fromJson("", EObject.class);
	}
}
