/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.ide.passwordSafe.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PasswordSafeConfigurable implements SearchableConfigurable, Configurable.NoScroll {
  final PasswordSafeSettings mySettings;

  PasswordSafeOptionsPanel myPanel;

  public PasswordSafeConfigurable(@NotNull PasswordSafeSettings settings) {
    mySettings = settings;
  }

  @Nls
  public String getDisplayName() {
    return "Passwords";
  }

  public String getHelpTopic() {
    return "reference.ide.settings.password.safe";
  }

  public JComponent createComponent() {
    myPanel = new PasswordSafeOptionsPanel();
    myPanel.reset(mySettings);
    return myPanel.getRoot();
  }

  public boolean isModified() {
    return myPanel != null && myPanel.isModified(mySettings);
  }

  public void apply() throws ConfigurationException {
    myPanel.apply(mySettings);
  }

  public void reset() {
    myPanel.reset(mySettings);
  }

  public void disposeUIResources() {
    myPanel = null;
  }

  @NotNull
  public String getId() {
    return "application.passwordSafe";
  }
}
