/**
 * Copyright (c) 2011-2018 EclipseSource Muenchen GmbH and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * EclipseSource Munich - initial API and implementation
 */
package org.eclipse.emfforms.coffee.model.coffee.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emfforms.coffee.model.coffee.Activity;
import org.eclipse.emfforms.coffee.model.coffee.BrewingUnit;
import org.eclipse.emfforms.coffee.model.coffee.CoffeeFactory;
import org.eclipse.emfforms.coffee.model.coffee.CoffeePackage;
import org.eclipse.emfforms.coffee.model.coffee.Component;
import org.eclipse.emfforms.coffee.model.coffee.ControlUnit;
import org.eclipse.emfforms.coffee.model.coffee.Dimension;
import org.eclipse.emfforms.coffee.model.coffee.DipTray;
import org.eclipse.emfforms.coffee.model.coffee.Display;
import org.eclipse.emfforms.coffee.model.coffee.Machine;
import org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess;
import org.eclipse.emfforms.coffee.model.coffee.Processor;
import org.eclipse.emfforms.coffee.model.coffee.RamType;
import org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType;
import org.eclipse.emfforms.coffee.model.coffee.WaterTank;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class CoffeePackageImpl extends EPackageImpl implements CoffeePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass machineEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass controlUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass brewingUnitEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dipTrayEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass waterTankEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass processorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass ramEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass activityEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dimensionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass displayEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum socketConnectorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum manufactoringProcessEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum ramTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method
	 * {@link #init init()}, which also performs initialization of the package, or
	 * returns the registered package, if one already exists. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emfforms.coffee.model.coffee.CoffeePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CoffeePackageImpl() {
		super(eNS_URI, CoffeeFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and
	 * for any others upon which it depends.
	 *
	 * <p>
	 * This method is used to initialize {@link CoffeePackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CoffeePackage init() {
		if (isInited)
			return (CoffeePackage) EPackage.Registry.INSTANCE.getEPackage(CoffeePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCoffeePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CoffeePackageImpl theCoffeePackage = registeredCoffeePackage instanceof CoffeePackageImpl
				? (CoffeePackageImpl) registeredCoffeePackage
				: new CoffeePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCoffeePackage.createPackageContents();

		// Initialize created meta-data
		theCoffeePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCoffeePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CoffeePackage.eNS_URI, theCoffeePackage);
		return theCoffeePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getComponent() {
		return componentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getComponent_Children() {
		return (EReference) componentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getComponent_Parent() {
		return (EReference) componentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getComponent_Activities() {
		return (EReference) componentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getMachine() {
		return machineEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMachine_Name() {
		return (EAttribute) machineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getControlUnit() {
		return controlUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getControlUnit_Processor() {
		return (EReference) controlUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getControlUnit_Dimension() {
		return (EReference) controlUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getControlUnit_Ram() {
		return (EReference) controlUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getControlUnit_Display() {
		return (EReference) controlUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getControlUnit_UserDescription() {
		return (EAttribute) controlUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getBrewingUnit() {
		return brewingUnitEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDipTray() {
		return dipTrayEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getWaterTank() {
		return waterTankEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getProcessor() {
		return processorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_Vendor() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_ClockSpeed() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_NumberOfCores() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_SocketconnectorType() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_ThermalDesignPower() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getProcessor_ManufactoringProcess() {
		return (EAttribute) processorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getRAM() {
		return ramEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getRAM_ClockSpeed() {
		return (EAttribute) ramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getRAM_Size() {
		return (EAttribute) ramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getRAM_Type() {
		return (EAttribute) ramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getActivity() {
		return activityEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getActivity_Name() {
		return (EAttribute) activityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDimension() {
		return dimensionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDimension_Width() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDimension_Height() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDimension_Length() {
		return (EAttribute) dimensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDisplay() {
		return displayEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDisplay_Width() {
		return (EAttribute) displayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDisplay_Height() {
		return (EAttribute) displayEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getSocketConnectorType() {
		return socketConnectorTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getManufactoringProcess() {
		return manufactoringProcessEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getRamType() {
		return ramTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public CoffeeFactory getCoffeeFactory() {
		return (CoffeeFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__CHILDREN);
		createEReference(componentEClass, COMPONENT__PARENT);
		createEReference(componentEClass, COMPONENT__ACTIVITIES);

		machineEClass = createEClass(MACHINE);
		createEAttribute(machineEClass, MACHINE__NAME);

		controlUnitEClass = createEClass(CONTROL_UNIT);
		createEReference(controlUnitEClass, CONTROL_UNIT__PROCESSOR);
		createEReference(controlUnitEClass, CONTROL_UNIT__DIMENSION);
		createEReference(controlUnitEClass, CONTROL_UNIT__RAM);
		createEReference(controlUnitEClass, CONTROL_UNIT__DISPLAY);
		createEAttribute(controlUnitEClass, CONTROL_UNIT__USER_DESCRIPTION);

		brewingUnitEClass = createEClass(BREWING_UNIT);

		dipTrayEClass = createEClass(DIP_TRAY);

		waterTankEClass = createEClass(WATER_TANK);

		processorEClass = createEClass(PROCESSOR);
		createEAttribute(processorEClass, PROCESSOR__VENDOR);
		createEAttribute(processorEClass, PROCESSOR__CLOCK_SPEED);
		createEAttribute(processorEClass, PROCESSOR__NUMBER_OF_CORES);
		createEAttribute(processorEClass, PROCESSOR__SOCKETCONNECTOR_TYPE);
		createEAttribute(processorEClass, PROCESSOR__THERMAL_DESIGN_POWER);
		createEAttribute(processorEClass, PROCESSOR__MANUFACTORING_PROCESS);

		ramEClass = createEClass(RAM);
		createEAttribute(ramEClass, RAM__CLOCK_SPEED);
		createEAttribute(ramEClass, RAM__SIZE);
		createEAttribute(ramEClass, RAM__TYPE);

		activityEClass = createEClass(ACTIVITY);
		createEAttribute(activityEClass, ACTIVITY__NAME);

		dimensionEClass = createEClass(DIMENSION);
		createEAttribute(dimensionEClass, DIMENSION__WIDTH);
		createEAttribute(dimensionEClass, DIMENSION__HEIGHT);
		createEAttribute(dimensionEClass, DIMENSION__LENGTH);

		displayEClass = createEClass(DISPLAY);
		createEAttribute(displayEClass, DISPLAY__WIDTH);
		createEAttribute(displayEClass, DISPLAY__HEIGHT);

		// Create enums
		socketConnectorTypeEEnum = createEEnum(SOCKET_CONNECTOR_TYPE);
		manufactoringProcessEEnum = createEEnum(MANUFACTORING_PROCESS);
		ramTypeEEnum = createEEnum(RAM_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This method is
	 * guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		machineEClass.getESuperTypes().add(this.getComponent());
		controlUnitEClass.getESuperTypes().add(this.getComponent());
		brewingUnitEClass.getESuperTypes().add(this.getComponent());
		dipTrayEClass.getESuperTypes().add(this.getComponent());
		waterTankEClass.getESuperTypes().add(this.getComponent());

		// Initialize classes, features, and operations; add parameters
		initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Children(), this.getComponent(), this.getComponent_Parent(), "children", null, 0, //$NON-NLS-1$
				-1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Parent(), this.getComponent(), this.getComponent_Children(), "parent", null, 0, 1, //$NON-NLS-1$
				Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Activities(), this.getActivity(), null, "activities", null, 0, -1, Component.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(machineEClass, Machine.class, "Machine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMachine_Name(), ecorePackage.getEString(), "name", null, 1, 1, Machine.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlUnitEClass, ControlUnit.class, "ControlUnit", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControlUnit_Processor(), this.getProcessor(), null, "processor", null, 1, 1, //$NON-NLS-1$
				ControlUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getControlUnit_Dimension(), this.getDimension(), null, "dimension", null, 1, 1, //$NON-NLS-1$
				ControlUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getControlUnit_Ram(), this.getRAM(), null, "ram", null, 1, -1, ControlUnit.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getControlUnit_Display(), this.getDisplay(), null, "display", null, 0, 1, ControlUnit.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getControlUnit_UserDescription(), ecorePackage.getEString(), "userDescription", null, 0, 1, //$NON-NLS-1$
				ControlUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(brewingUnitEClass, BrewingUnit.class, "BrewingUnit", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(dipTrayEClass, DipTray.class, "DipTray", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(waterTankEClass, WaterTank.class, "WaterTank", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(processorEClass, Processor.class, "Processor", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessor_Vendor(), ecorePackage.getEString(), "vendor", null, 0, 1, Processor.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessor_ClockSpeed(), ecorePackage.getEInt(), "clockSpeed", null, 0, 1, Processor.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessor_NumberOfCores(), ecorePackage.getEInt(), "numberOfCores", null, 0, 1, //$NON-NLS-1$
				Processor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessor_SocketconnectorType(), this.getSocketConnectorType(), "socketconnectorType", null, //$NON-NLS-1$
				0, 1, Processor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessor_ThermalDesignPower(), ecorePackage.getEInt(), "thermalDesignPower", null, 0, 1, //$NON-NLS-1$
				Processor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProcessor_ManufactoringProcess(), this.getManufactoringProcess(), "manufactoringProcess", //$NON-NLS-1$
				null, 0, 1, Processor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ramEClass, org.eclipse.emfforms.coffee.model.coffee.RAM.class, "RAM", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRAM_ClockSpeed(), ecorePackage.getEInt(), "clockSpeed", null, 0, 1, //$NON-NLS-1$
				org.eclipse.emfforms.coffee.model.coffee.RAM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRAM_Size(), ecorePackage.getEInt(), "size", null, 0, 1, //$NON-NLS-1$
				org.eclipse.emfforms.coffee.model.coffee.RAM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRAM_Type(), this.getRamType(), "type", null, 0, 1, //$NON-NLS-1$
				org.eclipse.emfforms.coffee.model.coffee.RAM.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activityEClass, Activity.class, "Activity", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActivity_Name(), ecorePackage.getEString(), "name", null, 1, 1, Activity.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dimensionEClass, Dimension.class, "Dimension", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDimension_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Dimension.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDimension_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Dimension.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDimension_Length(), ecorePackage.getEInt(), "length", null, 0, 1, Dimension.class, //$NON-NLS-1$
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(displayEClass, Display.class, "Display", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDisplay_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Display.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDisplay_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Display.class, !IS_TRANSIENT, //$NON-NLS-1$
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(socketConnectorTypeEEnum, SocketConnectorType.class, "SocketConnectorType"); //$NON-NLS-1$
		addEEnumLiteral(socketConnectorTypeEEnum, SocketConnectorType.A1T);
		addEEnumLiteral(socketConnectorTypeEEnum, SocketConnectorType.Z51);

		initEEnum(manufactoringProcessEEnum, ManufactoringProcess.class, "ManufactoringProcess"); //$NON-NLS-1$
		addEEnumLiteral(manufactoringProcessEEnum, ManufactoringProcess.NM18);
		addEEnumLiteral(manufactoringProcessEEnum, ManufactoringProcess.NM25);

		initEEnum(ramTypeEEnum, RamType.class, "RamType"); //$NON-NLS-1$
		addEEnumLiteral(ramTypeEEnum, RamType.SODIMM);
		addEEnumLiteral(ramTypeEEnum, RamType.SIDIMM);

		// Create resource
		createResource(eNS_URI);
	}

} // CoffeePackageImpl
