<?xml version="1.0" encoding="UTF-8"?>
<com.eclipsesource.emfforms.coffee.model:Machine
    xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:com.eclipsesource.emfforms.coffee.model="http://www.eclipsesource.com/emfforms/example/coffeemodel"
    xsi:schemaLocation="http://www.eclipsesource.com/emfforms/example/coffeemodel Coffee.ecore"
    name="Super Brewer 3000">
  <children
      xsi:type="com.eclipsesource.emfforms.coffee.model:BrewingUnit"/>
  <children
      xsi:type="com.eclipsesource.emfforms.coffee.model:ControlUnit">
    <processor
        clockSpeed="5"
        numberOfCores="10"
        socketconnectorType="Z51"
        thermalDesignPower="100"/>
    <display
        width="10"
        height="20"/>
  </children>
  <workflows>
    <nodes xsi:type="com.eclipsesource.emfforms.coffee.model:AutomaticTask"
        name="PreHeat"
        component="//@children.0"/>
  </workflows>
</com.eclipsesource.emfforms.coffee.model:Machine>
