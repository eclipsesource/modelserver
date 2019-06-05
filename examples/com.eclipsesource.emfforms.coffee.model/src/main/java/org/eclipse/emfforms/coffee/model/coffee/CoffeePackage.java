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
package org.eclipse.emfforms.coffee.model.coffee;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.emfforms.coffee.model.coffee.CoffeeFactory
 * @model kind="package"
 * @generated
 */
public interface CoffeePackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "coffee"; //$NON-NLS-1$

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emfforms/example/coffeemodel"; //$NON-NLS-1$

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.emfforms.coffee.model"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	CoffeePackage eINSTANCE = org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl.init();

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl
	 * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CHILDREN = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PARENT = 1;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ACTIVITIES = 2;

	/**
	 * The number of structural features of the '<em>Component</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Component</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.MachineImpl
	 * <em>Machine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.MachineImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getMachine()
	 * @generated
	 */
	int MACHINE = 1;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE__CHILDREN = COMPONENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE__PARENT = COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE__ACTIVITIES = COMPONENT__ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE__NAME = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Machine</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Machine</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MACHINE_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ControlUnitImpl
	 * <em>Control Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ControlUnitImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getControlUnit()
	 * @generated
	 */
	int CONTROL_UNIT = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__CHILDREN = COMPONENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__PARENT = COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__ACTIVITIES = COMPONENT__ACTIVITIES;

	/**
	 * The feature id for the '<em><b>Processor</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__PROCESSOR = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__DIMENSION = COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ram</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__RAM = COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Display</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__DISPLAY = COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>User Description</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT__USER_DESCRIPTION = COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Control Unit</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Control Unit</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CONTROL_UNIT_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.BrewingUnitImpl
	 * <em>Brewing Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.BrewingUnitImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getBrewingUnit()
	 * @generated
	 */
	int BREWING_UNIT = 3;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BREWING_UNIT__CHILDREN = COMPONENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BREWING_UNIT__PARENT = COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BREWING_UNIT__ACTIVITIES = COMPONENT__ACTIVITIES;

	/**
	 * The number of structural features of the '<em>Brewing Unit</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BREWING_UNIT_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Brewing Unit</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BREWING_UNIT_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DipTrayImpl <em>Dip
	 * Tray</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DipTrayImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDipTray()
	 * @generated
	 */
	int DIP_TRAY = 4;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIP_TRAY__CHILDREN = COMPONENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIP_TRAY__PARENT = COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIP_TRAY__ACTIVITIES = COMPONENT__ACTIVITIES;

	/**
	 * The number of structural features of the '<em>Dip Tray</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIP_TRAY_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Dip Tray</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIP_TRAY_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.WaterTankImpl <em>Water
	 * Tank</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.WaterTankImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getWaterTank()
	 * @generated
	 */
	int WATER_TANK = 5;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WATER_TANK__CHILDREN = COMPONENT__CHILDREN;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WATER_TANK__PARENT = COMPONENT__PARENT;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WATER_TANK__ACTIVITIES = COMPONENT__ACTIVITIES;

	/**
	 * The number of structural features of the '<em>Water Tank</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WATER_TANK_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Water Tank</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WATER_TANK_OPERATION_COUNT = COMPONENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ProcessorImpl
	 * <em>Processor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ProcessorImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getProcessor()
	 * @generated
	 */
	int PROCESSOR = 6;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__VENDOR = 0;

	/**
	 * The feature id for the '<em><b>Clock Speed</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__CLOCK_SPEED = 1;

	/**
	 * The feature id for the '<em><b>Number Of Cores</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__NUMBER_OF_CORES = 2;

	/**
	 * The feature id for the '<em><b>Socketconnector Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__SOCKETCONNECTOR_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Thermal Design Power</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__THERMAL_DESIGN_POWER = 4;

	/**
	 * The feature id for the '<em><b>Manufactoring Process</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR__MANUFACTORING_PROCESS = 5;

	/**
	 * The number of structural features of the '<em>Processor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Processor</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROCESSOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.RAMImpl <em>RAM</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.RAMImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getRAM()
	 * @generated
	 */
	int RAM = 7;

	/**
	 * The feature id for the '<em><b>Clock Speed</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAM__CLOCK_SPEED = 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAM__SIZE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAM__TYPE = 2;

	/**
	 * The number of structural features of the '<em>RAM</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAM_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>RAM</em>' class. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RAM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ActivityImpl
	 * <em>Activity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ActivityImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Activity</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Activity</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DimensionImpl
	 * <em>Dimension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DimensionImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 9;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIMENSION__WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIMENSION__HEIGHT = 1;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIMENSION__LENGTH = 2;

	/**
	 * The number of structural features of the '<em>Dimension</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Dimension</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DIMENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DisplayImpl
	 * <em>Display</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DisplayImpl
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDisplay()
	 * @generated
	 */
	int DISPLAY = 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISPLAY__WIDTH = 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISPLAY__HEIGHT = 1;

	/**
	 * The number of structural features of the '<em>Display</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISPLAY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Display</em>' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISPLAY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
	 * <em>Socket Connector Type</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getSocketConnectorType()
	 * @generated
	 */
	int SOCKET_CONNECTOR_TYPE = 11;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
	 * <em>Manufactoring Process</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getManufactoringProcess()
	 * @generated
	 */
	int MANUFACTORING_PROCESS = 12;

	/**
	 * The meta object id for the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RamType <em>Ram Type</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emfforms.coffee.model.coffee.RamType
	 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getRamType()
	 * @generated
	 */
	int RAM_TYPE = 13;

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Component
	 * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Component#getChildren
	 * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list
	 *         '<em>Children</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Component#getChildren()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Children();

	/**
	 * Returns the meta object for the container reference
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Component#getParent
	 * <em>Parent</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Component#getParent()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Parent();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Component#getActivities
	 * <em>Activities</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list
	 *         '<em>Activities</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Component#getActivities()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Activities();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Machine <em>Machine</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Machine</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Machine
	 * @generated
	 */
	EClass getMachine();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Machine#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Machine#getName()
	 * @see #getMachine()
	 * @generated
	 */
	EAttribute getMachine_Name();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit <em>Control
	 * Unit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Control Unit</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit
	 * @generated
	 */
	EClass getControlUnit();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getProcessor
	 * <em>Processor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Processor</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getProcessor()
	 * @see #getControlUnit()
	 * @generated
	 */
	EReference getControlUnit_Processor();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getDimension
	 * <em>Dimension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Dimension</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getDimension()
	 * @see #getControlUnit()
	 * @generated
	 */
	EReference getControlUnit_Dimension();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getRam
	 * <em>Ram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ram</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getRam()
	 * @see #getControlUnit()
	 * @generated
	 */
	EReference getControlUnit_Ram();

	/**
	 * Returns the meta object for the containment reference
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getDisplay
	 * <em>Display</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Display</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getDisplay()
	 * @see #getControlUnit()
	 * @generated
	 */
	EReference getControlUnit_Display();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getUserDescription
	 * <em>User Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>User Description</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ControlUnit#getUserDescription()
	 * @see #getControlUnit()
	 * @generated
	 */
	EAttribute getControlUnit_UserDescription();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.BrewingUnit <em>Brewing
	 * Unit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Brewing Unit</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.BrewingUnit
	 * @generated
	 */
	EClass getBrewingUnit();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.DipTray <em>Dip Tray</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Dip Tray</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.DipTray
	 * @generated
	 */
	EClass getDipTray();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.WaterTank <em>Water
	 * Tank</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Water Tank</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.WaterTank
	 * @generated
	 */
	EClass getWaterTank();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor
	 * <em>Processor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Processor</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor
	 * @generated
	 */
	EClass getProcessor();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getVendor
	 * <em>Vendor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getVendor()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_Vendor();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getClockSpeed
	 * <em>Clock Speed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Clock Speed</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getClockSpeed()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_ClockSpeed();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getNumberOfCores
	 * <em>Number Of Cores</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Number Of Cores</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getNumberOfCores()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_NumberOfCores();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getSocketconnectorType
	 * <em>Socketconnector Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Socketconnector Type</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getSocketconnectorType()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_SocketconnectorType();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getThermalDesignPower
	 * <em>Thermal Design Power</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Thermal Design Power</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getThermalDesignPower()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_ThermalDesignPower();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Processor#getManufactoringProcess
	 * <em>Manufactoring Process</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Manufactoring Process</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Processor#getManufactoringProcess()
	 * @see #getProcessor()
	 * @generated
	 */
	EAttribute getProcessor_ManufactoringProcess();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RAM <em>RAM</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>RAM</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.RAM
	 * @generated
	 */
	EClass getRAM();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RAM#getClockSpeed <em>Clock
	 * Speed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Clock Speed</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.RAM#getClockSpeed()
	 * @see #getRAM()
	 * @generated
	 */
	EAttribute getRAM_ClockSpeed();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RAM#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.RAM#getSize()
	 * @see #getRAM()
	 * @generated
	 */
	EAttribute getRAM_Size();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RAM#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.RAM#getType()
	 * @see #getRAM()
	 * @generated
	 */
	EAttribute getRAM_Type();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Activity
	 * <em>Activity</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Activity#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Activity#getName()
	 * @see #getActivity()
	 * @generated
	 */
	EAttribute getActivity_Name();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Dimension
	 * <em>Dimension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Dimension</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Dimension
	 * @generated
	 */
	EClass getDimension();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Dimension#getWidth
	 * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Dimension#getWidth()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_Width();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Dimension#getHeight
	 * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Dimension#getHeight()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_Height();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Dimension#getLength
	 * <em>Length</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Dimension#getLength()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_Length();

	/**
	 * Returns the meta object for class
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Display <em>Display</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Display</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Display
	 * @generated
	 */
	EClass getDisplay();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Display#getWidth
	 * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Display#getWidth()
	 * @see #getDisplay()
	 * @generated
	 */
	EAttribute getDisplay_Width();

	/**
	 * Returns the meta object for the attribute
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Display#getHeight
	 * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.Display#getHeight()
	 * @see #getDisplay()
	 * @generated
	 */
	EAttribute getDisplay_Height();

	/**
	 * Returns the meta object for enum
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
	 * <em>Socket Connector Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Socket Connector Type</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
	 * @generated
	 */
	EEnum getSocketConnectorType();

	/**
	 * Returns the meta object for enum
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
	 * <em>Manufactoring Process</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Manufactoring Process</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
	 * @generated
	 */
	EEnum getManufactoringProcess();

	/**
	 * Returns the meta object for enum
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.RamType <em>Ram Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Ram Type</em>'.
	 * @see org.eclipse.emfforms.coffee.model.coffee.RamType
	 * @generated
	 */
	EEnum getRamType();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoffeeFactory getCoffeeFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl
		 * <em>Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT__CHILDREN = eINSTANCE.getComponent_Children();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT__PARENT = eINSTANCE.getComponent_Parent();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' containment
		 * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference COMPONENT__ACTIVITIES = eINSTANCE.getComponent_Activities();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.MachineImpl
		 * <em>Machine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.MachineImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getMachine()
		 * @generated
		 */
		EClass MACHINE = eINSTANCE.getMachine();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MACHINE__NAME = eINSTANCE.getMachine_Name();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ControlUnitImpl
		 * <em>Control Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ControlUnitImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getControlUnit()
		 * @generated
		 */
		EClass CONTROL_UNIT = eINSTANCE.getControlUnit();

		/**
		 * The meta object literal for the '<em><b>Processor</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTROL_UNIT__PROCESSOR = eINSTANCE.getControlUnit_Processor();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTROL_UNIT__DIMENSION = eINSTANCE.getControlUnit_Dimension();

		/**
		 * The meta object literal for the '<em><b>Ram</b></em>' containment reference
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTROL_UNIT__RAM = eINSTANCE.getControlUnit_Ram();

		/**
		 * The meta object literal for the '<em><b>Display</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CONTROL_UNIT__DISPLAY = eINSTANCE.getControlUnit_Display();

		/**
		 * The meta object literal for the '<em><b>User Description</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CONTROL_UNIT__USER_DESCRIPTION = eINSTANCE.getControlUnit_UserDescription();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.BrewingUnitImpl
		 * <em>Brewing Unit</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.BrewingUnitImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getBrewingUnit()
		 * @generated
		 */
		EClass BREWING_UNIT = eINSTANCE.getBrewingUnit();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DipTrayImpl <em>Dip
		 * Tray</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DipTrayImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDipTray()
		 * @generated
		 */
		EClass DIP_TRAY = eINSTANCE.getDipTray();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.WaterTankImpl <em>Water
		 * Tank</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.WaterTankImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getWaterTank()
		 * @generated
		 */
		EClass WATER_TANK = eINSTANCE.getWaterTank();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ProcessorImpl
		 * <em>Processor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ProcessorImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getProcessor()
		 * @generated
		 */
		EClass PROCESSOR = eINSTANCE.getProcessor();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__VENDOR = eINSTANCE.getProcessor_Vendor();

		/**
		 * The meta object literal for the '<em><b>Clock Speed</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__CLOCK_SPEED = eINSTANCE.getProcessor_ClockSpeed();

		/**
		 * The meta object literal for the '<em><b>Number Of Cores</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__NUMBER_OF_CORES = eINSTANCE.getProcessor_NumberOfCores();

		/**
		 * The meta object literal for the '<em><b>Socketconnector Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__SOCKETCONNECTOR_TYPE = eINSTANCE.getProcessor_SocketconnectorType();

		/**
		 * The meta object literal for the '<em><b>Thermal Design Power</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__THERMAL_DESIGN_POWER = eINSTANCE.getProcessor_ThermalDesignPower();

		/**
		 * The meta object literal for the '<em><b>Manufactoring Process</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROCESSOR__MANUFACTORING_PROCESS = eINSTANCE.getProcessor_ManufactoringProcess();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.RAMImpl <em>RAM</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.RAMImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getRAM()
		 * @generated
		 */
		EClass RAM = eINSTANCE.getRAM();

		/**
		 * The meta object literal for the '<em><b>Clock Speed</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RAM__CLOCK_SPEED = eINSTANCE.getRAM_ClockSpeed();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RAM__SIZE = eINSTANCE.getRAM_Size();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RAM__TYPE = eINSTANCE.getRAM_Type();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.ActivityImpl
		 * <em>Activity</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.ActivityImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ACTIVITY__NAME = eINSTANCE.getActivity_Name();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DimensionImpl
		 * <em>Dimension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DimensionImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDimension()
		 * @generated
		 */
		EClass DIMENSION = eINSTANCE.getDimension();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DIMENSION__WIDTH = eINSTANCE.getDimension_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DIMENSION__HEIGHT = eINSTANCE.getDimension_Height();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DIMENSION__LENGTH = eINSTANCE.getDimension_Length();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.impl.DisplayImpl
		 * <em>Display</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.DisplayImpl
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getDisplay()
		 * @generated
		 */
		EClass DISPLAY = eINSTANCE.getDisplay();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DISPLAY__WIDTH = eINSTANCE.getDisplay_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DISPLAY__HEIGHT = eINSTANCE.getDisplay_Height();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
		 * <em>Socket Connector Type</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.SocketConnectorType
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getSocketConnectorType()
		 * @generated
		 */
		EEnum SOCKET_CONNECTOR_TYPE = eINSTANCE.getSocketConnectorType();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
		 * <em>Manufactoring Process</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.ManufactoringProcess
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getManufactoringProcess()
		 * @generated
		 */
		EEnum MANUFACTORING_PROCESS = eINSTANCE.getManufactoringProcess();

		/**
		 * The meta object literal for the
		 * '{@link org.eclipse.emfforms.coffee.model.coffee.RamType <em>Ram Type</em>}'
		 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.emfforms.coffee.model.coffee.RamType
		 * @see org.eclipse.emfforms.coffee.model.coffee.impl.CoffeePackageImpl#getRamType()
		 * @generated
		 */
		EEnum RAM_TYPE = eINSTANCE.getRamType();

	}

} // CoffeePackage
