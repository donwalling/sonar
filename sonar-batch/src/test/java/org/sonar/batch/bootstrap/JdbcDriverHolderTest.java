/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.batch.bootstrap;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class JdbcDriverHolderTest {

  @Test
  public void testClassLoader() throws URISyntaxException {
    /* foo.jar has just one file /foo/foo.txt */
    assertNull(getClass().getClassLoader().getResource("foo/foo.txt"));

    URL url = getClass().getResource("/org/sonar/batch/bootstrap/JdbcDriverHolderTest/foo.jar");
    JdbcDriverHolder classloader = new JdbcDriverHolder(new File(url.toURI()));
    assertNotNull(classloader.getClassLoader());
    assertNotNull(classloader.getClassLoader().getResource("foo/foo.txt"));

    classloader.stop();
    assertNull(classloader.getClassLoader());
  }

}
