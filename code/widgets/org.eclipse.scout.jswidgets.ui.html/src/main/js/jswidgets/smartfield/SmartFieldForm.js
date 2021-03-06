/*******************************************************************************
 * Copyright (c) 2017 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Distribution License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/org/documents/edl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
jswidgets.SmartFieldForm = function() {
  jswidgets.SmartFieldForm.parent.call(this);
};
scout.inherits(jswidgets.SmartFieldForm, scout.Form);

jswidgets.SmartFieldForm.prototype._jsonModel = function() {
  return scout.models.getModel('jswidgets.SmartFieldForm');
};

jswidgets.SmartFieldForm.prototype._init = function(model) {
  jswidgets.SmartFieldForm.parent.prototype._init.call(this, model);

  this.smartField = this.widget('SmartField');

  this.widget('PropertiesBox').setField(this.smartField);
  this.widget('ValueFieldPropertiesBox').setField(this.smartField);
  this.widget('FormFieldPropertiesBox').setField(this.smartField);
};
