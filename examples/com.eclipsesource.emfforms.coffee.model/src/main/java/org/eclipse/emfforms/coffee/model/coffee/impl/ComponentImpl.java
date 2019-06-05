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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emfforms.coffee.model.coffee.Activity;
import org.eclipse.emfforms.coffee.model.coffee.CoffeePackage;
import org.eclipse.emfforms.coffee.model.coffee.Component;

/**
 * <!-- begin-user-doc --> An implementation of the model object
 * '<em><b>Component</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl#getChildren
 * <em>Children</em>}</li>
 * <li>{@link org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl#getParent
 * <em>Parent</em>}</li>
 * <li>{@link org.eclipse.emfforms.coffee.model.coffee.impl.ComponentImpl#getActivities
 * <em>Activities</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ComponentImpl extends MinimalEObjectImpl.Container implements Component {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> children;

	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity> activities;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CoffeePackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Component> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<Component>(Component.class, this,
					CoffeePackage.COMPONENT__CHILDREN, CoffeePackage.COMPONENT__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Component getParent() {
		if (eContainerFeatureID() != CoffeePackage.COMPONENT__PARENT)
			return null;
		return (Component) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetParent(Component newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newParent, CoffeePackage.COMPONENT__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParent(Component newParent) {
		if (newParent != eInternalContainer()
				|| (eContainerFeatureID() != CoffeePackage.COMPONENT__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject) newParent).eInverseAdd(this, CoffeePackage.COMPONENT__CHILDREN,
						Component.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CoffeePackage.COMPONENT__PARENT, newParent,
					newParent));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Activity> getActivities() {
		if (activities == null) {
			activities = new EObjectContainmentEList<Activity>(Activity.class, this,
					CoffeePackage.COMPONENT__ACTIVITIES);
		}
		return activities;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getChildren()).basicAdd(otherEnd, msgs);
		case CoffeePackage.COMPONENT__PARENT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetParent((Component) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
		case CoffeePackage.COMPONENT__PARENT:
			return basicSetParent(null, msgs);
		case CoffeePackage.COMPONENT__ACTIVITIES:
			return ((InternalEList<?>) getActivities()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case CoffeePackage.COMPONENT__PARENT:
			return eInternalContainer().eInverseRemove(this, CoffeePackage.COMPONENT__CHILDREN, Component.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			return getChildren();
		case CoffeePackage.COMPONENT__PARENT:
			return getParent();
		case CoffeePackage.COMPONENT__ACTIVITIES:
			return getActivities();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			getChildren().clear();
			getChildren().addAll((Collection<? extends Component>) newValue);
			return;
		case CoffeePackage.COMPONENT__PARENT:
			setParent((Component) newValue);
			return;
		case CoffeePackage.COMPONENT__ACTIVITIES:
			getActivities().clear();
			getActivities().addAll((Collection<? extends Activity>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			getChildren().clear();
			return;
		case CoffeePackage.COMPONENT__PARENT:
			setParent((Component) null);
			return;
		case CoffeePackage.COMPONENT__ACTIVITIES:
			getActivities().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case CoffeePackage.COMPONENT__CHILDREN:
			return children != null && !children.isEmpty();
		case CoffeePackage.COMPONENT__PARENT:
			return getParent() != null;
		case CoffeePackage.COMPONENT__ACTIVITIES:
			return activities != null && !activities.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // ComponentImpl
