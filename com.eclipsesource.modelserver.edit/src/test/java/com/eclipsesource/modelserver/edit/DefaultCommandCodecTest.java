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

package com.eclipsesource.modelserver.edit;

import static com.eclipsesource.modelserver.edit.tests.util.EMFMatchers.commandEqualTo;
import static org.eclipse.emf.common.notify.Notification.NO_INDEX;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.eclipsesource.modelserver.command.CCommand;
import com.eclipsesource.modelserver.command.CCommandFactory;
import com.eclipsesource.modelserver.command.CommandKind;
import com.eclipsesource.modelserver.common.codecs.DecodingException;
import com.eclipsesource.modelserver.common.codecs.EMFJsonConverter;
import com.eclipsesource.modelserver.common.codecs.EncodingException;
import com.eclipsesource.modelserver.edit.tests.util.EMFMatchers;

/**
 * Test cases for the {@link DefaultCommandCodec} class.
 */
@RunWith(Parameterized.class)
public class DefaultCommandCodecTest {

	private static final String ATTRIBUTE = "attribute";
	private static final String REFERENCE = "reference";

	private static ResourceSet resourceSet;
	private static EditingDomain domain;
	private static EPackage ePackage;

	private final Command editCommand;
	private final CCommand commandModel;

	/**
	 * Initializes me.
	 */
	public DefaultCommandCodecTest(CommandKind type, String featureKind, Command editCommand, CCommand commandModel) {
		super();

		this.editCommand = editCommand;
		this.commandModel = commandModel;
	}

	@Test
	public void encode() throws EncodingException {
		CCommand encoded = new DefaultCommandCodec().encode(editCommand);
		assertThat(encoded, EMFMatchers.eEqualTo(commandModel));
	}

	@Test
	public void decode() throws DecodingException {
		Command decoded = new DefaultCommandCodec().decode(domain, commandModel);
		assertThat(decoded, commandEqualTo(editCommand));
	}

	//
	// Test framework
	//

	@Parameters(name = "{0} {1}")
	public static Iterable<Object[]> parameters() {
		initializeResourceSet();

		return Arrays.asList(new Object[][] { //
				new Object[] { CommandKind.SET, ATTRIBUTE, createAttributeSetCommand(), createAttributeSetModel() }, //
				new Object[] { CommandKind.SET, REFERENCE, createReferenceSetCommand(), createReferenceSetModel() }, //
		});
	}

	private static void initializeResourceSet() {
		domain = new AdapterFactoryEditingDomain(new EcoreAdapterFactory(), new BasicCommandStack());
		resourceSet = domain.getResourceSet();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("json",
				new JsonResourceFactory(EMFJsonConverter.setupDefaultMapper()));
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore",
				new EcoreResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.getResource(URI.createFileURI("src/test/resources/Coffee.ecore"), true);
		ePackage = (EPackage) resource.getContents().get(0);
	}

	static Command createAttributeSetCommand() {
		return SetCommand.create(domain, ePackage, EcorePackage.Literals.ENAMED_ELEMENT__NAME, "Foo");
	}

	static CCommand createAttributeSetModel() {
		CCommand result = CCommandFactory.eINSTANCE.createCommand();
		result.setType(CommandKind.SET);
		result.setOwner(ePackage);
		result.setFeature("name");
		result.getDataValues().add("Foo");
		result.getIndices().add(NO_INDEX);
		return result;
	}

	static Command createReferenceSetCommand() {
		EClass newClass = EcoreFactory.eINSTANCE.createEClass();
		newClass.setName("Foo");

		return SetCommand.create(domain, ePackage, EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, newClass, 1);
	}

	static CCommand createReferenceSetModel() {
		EClass newClass = EcoreFactory.eINSTANCE.createEClass();
		newClass.setName("Foo");

		CCommand result = CCommandFactory.eINSTANCE.createCommand();
		result.setType(CommandKind.SET);
		result.setOwner(ePackage);
		result.setFeature("eClassifiers");
		result.getObjectValues().add(newClass);
		result.getObjectsToAdd().add(newClass);
		result.getIndices().add(1);
		return result;
	}

}
