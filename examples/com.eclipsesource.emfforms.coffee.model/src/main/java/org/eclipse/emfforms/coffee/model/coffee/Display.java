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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object
 * '<em><b>Display</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.emfforms.coffee.model.coffee.Display#getWidth
 * <em>Width</em>}</li>
 * <li>{@link org.eclipse.emfforms.coffee.model.coffee.Display#getHeight
 * <em>Height</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emfforms.coffee.model.coffee.CoffeePackage#getDisplay()
 * @model
 * @generated
 */
public interface Display extends EObject {
	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see org.eclipse.emfforms.coffee.model.coffee.CoffeePackage#getDisplay_Width()
	 * @model
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Display#getWidth
	 * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(int value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see org.eclipse.emfforms.coffee.model.coffee.CoffeePackage#getDisplay_Height()
	 * @model
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the
	 * '{@link org.eclipse.emfforms.coffee.model.coffee.Display#getHeight
	 * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

} // Display
