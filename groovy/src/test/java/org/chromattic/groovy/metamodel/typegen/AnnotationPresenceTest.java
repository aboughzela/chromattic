/*
* Copyright (C) 2003-2009 eXo Platform SAS.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.chromattic.groovy.metamodel.typegen;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class AnnotationPresenceTest extends TestCase {
  private static final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
  private static final GroovyShell groovyShell = new GroovyShell(groovyClassLoader);

  public AnnotationPresenceTest() {
    groovyClassLoader.parseClass(
      "import org.chromattic.api.annotations.Name\n" +
      "import org.chromattic.api.annotations.Property\n" +
      "import org.chromattic.api.annotations.PrimaryType\n" +
      "@PrimaryType( name=\"a\")" +
      "class A {\n" +
      "  public @Name def String stringTypedChromattic\n" +
      "  public @Property def String stringTypedChromatticExplicitGetter\n" +
      "  public String getStringTypedChromatticExplicitGetter() {\n" +
      "    return stringTypedChromatticExplicitGetter\n" +
      "  }\n" +
      "}"
    );
  }

  public void testAnnotationFieldPresent() {
    Object eval = groovyShell.evaluate("new A().getClass().getDeclaredField(\"stringTypedChromattic\").getAnnotations().length");
    assertEquals(eval, 0);
  }

  public void testAnnotationImplicitGetterPresent() {
    assertTrue((Boolean) groovyShell.evaluate(
      "import org.chromattic.api.annotations.Name\n" +
      "new A().getClass().getDeclaredMethod(\"getStringTypedChromattic\").isAnnotationPresent(Name.class)")
    );
  }

  public void testAnnotationExplicitGetterPresent() {
    assertTrue((Boolean) groovyShell.evaluate(
      "import org.chromattic.api.annotations.Property\n" +
      "new A().getClass().getDeclaredMethod(\"getStringTypedChromatticExplicitGetter\").isAnnotationPresent(Property.class)")
    );
  }

  public void testAnnotationImplicitSetterPresent() {
    assertTrue((Boolean) groovyShell.evaluate(
      "import org.chromattic.api.annotations.SetterDelegation\n" +
      "new A().getClass().getDeclaredMethod(\"setStringTypedChromattic\", String.class).isAnnotationPresent(SetterDelegation.class)")
    );
  }

  public void testAnnotationExplicitSetterPresent() {
    assertTrue((Boolean) groovyShell.evaluate(
      "import org.chromattic.api.annotations.SetterDelegation\n" +
      "new A().getClass().getDeclaredMethod(\"setStringTypedChromatticExplicitGetter\", String.class).isAnnotationPresent(SetterDelegation.class)")
    );
  }

}