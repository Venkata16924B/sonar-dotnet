/*
 * Sonar C# Plugin :: Core
 * Copyright (C) 2010 Jose Chillan, Alexandre Victoor and SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.plugins.csharp;

import org.sonar.plugins.csharp.CSharpSourceCodeColorizer;

import org.sonar.plugins.csharp.CSharpRuleProfile;
import org.sonar.plugins.csharp.CSharpRuleRepository;
import org.sonar.plugins.csharp.CSharp;
import org.sonar.plugins.csharp.CSharpCodeCoverageProvider;
import org.sonar.plugins.csharp.CSharpCommonRulesDecorator;
import org.sonar.plugins.csharp.CSharpCommonRulesEngine;
import org.sonar.plugins.csharp.CSharpPlugin;
import org.sonar.plugins.csharp.CSharpFxCopProvider;
import org.sonar.plugins.csharp.CSharpSourceImporter;
import org.sonar.plugins.csharp.CSharpUnitTestResultsProvider;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.plugins.csharp.CSharpSensor;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class CSharpPluginTest {

  @Test
  public void getExtensions() {
    assertThat(nonProperties(new CSharpPlugin().getExtensions())).contains(
      CSharp.class,
      CSharpSourceImporter.class,
      CSharpCommonRulesEngine.class,
      CSharpCommonRulesDecorator.class,
      CSharpSourceCodeColorizer.class,
      CSharpRuleRepository.class,
      CSharpRuleProfile.class,
      CSharpSensor.class);

    assertThat(new CSharpPlugin().getExtensions()).hasSize(
      8
        + CSharpFxCopProvider.extensions().size()
        + CSharpCodeCoverageProvider.extensions().size()
        + CSharpUnitTestResultsProvider.extensions().size());
  }

  private static List nonProperties(List extensions) {
    ImmutableList.Builder builder = ImmutableList.builder();
    for (Object extension : extensions) {
      if (!(extension instanceof PropertyDefinition)) {
        builder.add(extension);
      }
    }
    return builder.build();
  }

}