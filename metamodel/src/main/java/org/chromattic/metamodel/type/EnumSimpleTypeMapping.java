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

package org.chromattic.metamodel.type;

import org.chromattic.metamodel.mapping.jcr.PropertyMetaType;
import org.chromattic.spi.type.SimpleTypeProvider;
import org.reflext.api.ClassTypeInfo;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public class EnumSimpleTypeMapping implements SimpleTypeMapping {

  /** . */
  private final ClassTypeInfo enumInfo;

  public EnumSimpleTypeMapping(ClassTypeInfo enumInfo) {
    this.enumInfo = enumInfo;
  }

  public PropertyMetaType<String> getPropertyMetaType() {
    return PropertyMetaType.STRING;
  }

  public SimpleTypeProvider<?, ?> create() {
    // todo : maybe need a cache here?
    Class clazz = (Class<Object>)enumInfo.unwrap();
    return new EnumSimpleTypeProvider(clazz);
  }
}
