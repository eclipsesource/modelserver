/**
 *  Copyright (c) 2019 EclipseSource and others.
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
 * 
 */
package com.eclipsesource.emfforms.coffee.model.coffee;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Weighted
 * Flow</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.eclipsesource.emfforms.coffee.model.coffee.WeightedFlow#getProbability
 * <em>Probability</em>}</li>
 * </ul>
 *
 * @see com.eclipsesource.emfforms.coffee.model.coffee.CoffeePackage#getWeightedFlow()
 * @model
 * @generated
 */
public interface WeightedFlow extends Flow {
	/**
	 * Returns the value of the '<em><b>Probability</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Probability</em>' attribute.
	 * @see #setProbability(double)
	 * @see com.eclipsesource.emfforms.coffee.model.coffee.CoffeePackage#getWeightedFlow_Probability()
	 * @model
	 * @generated
	 */
	double getProbability();

	/**
	 * Sets the value of the
	 * '{@link com.eclipsesource.emfforms.coffee.model.coffee.WeightedFlow#getProbability
	 * <em>Probability</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value the new value of the '<em>Probability</em>' attribute.
	 * @see #getProbability()
	 * @generated
	 */
	void setProbability(double value);

} // WeightedFlow
