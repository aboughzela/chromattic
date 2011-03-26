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

package org.chromattic.core.bean;

import org.chromattic.api.BuilderException;
import org.chromattic.api.annotations.DefaultValue;
import org.reflext.api.ClassTypeInfo;
import org.reflext.api.SimpleTypeInfo;

import java.lang.annotation.Annotation;
import java.io.InputStream;
import java.util.*;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class SimpleValueInfo<V> extends ValueInfo {

  /** . */
  private final SimpleType<V> simpleType;

  /** . */
  private List<V> defaultValue;

  SimpleValueInfo(ClassTypeInfo typeInfo, SimpleType<V> simpleType, List<V> defaultValue) {
    super(typeInfo);

    // Make a safe clone to prevent modifications and make the object immutable
    if (defaultValue != null) {
      defaultValue = Collections.unmodifiableList(new ArrayList<V>(defaultValue));
    }

    //
    this.simpleType = simpleType;
    this.defaultValue = defaultValue;
  }

  public SimpleType<V> getSimpleType() {
    return simpleType;
  }

  public List<V> getDefaultValue() {
    return defaultValue;
  }

  @Override
  public String toString() {
    return "SimpleValueInfo[simpleType=" + simpleType + "]";
  }

  public static SimpleValueInfo<String> createPath(ClassTypeInfo typeInfo) {
    if (typeInfo == null) {
      throw new NullPointerException();
    }

    //
    if (typeInfo.getName().equals(String.class.getName())) {
      return new SimpleValueInfo<String>(typeInfo, ObjectSimpleType.PATH, null);
    } else {
      throw new IllegalArgumentException("Simple value of type path must have a type of " + String.class.getName());
    }
  }

}
