/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
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

package org.chromattic.metatype;

import java.util.Map;

public interface ObjectType {

  String getName();

  Map<String, ? extends PropertyDescriptor<?>> getProperties();

  PropertyDescriptor<?> getProperty(String name) throws NullPointerException;

  PropertyDescriptor<?> resolveProperty(String name) throws NullPointerException;

  Map<String, ? extends HierarchicalRelationshipDescriptor> getChildrenRelationships();

  HierarchicalRelationshipDescriptor getChildRelationship(String name) throws NullPointerException;

  Map<String, ? extends InheritanceRelationshipDescriptor> getSuperRelationships();

  Map<String, ? extends InheritanceRelationshipDescriptor> getSuperEntityRelationships();

  Map<String, ? extends InheritanceRelationshipDescriptor> getSuperMixinRelationships();

  InheritanceRelationshipDescriptor getSuperRelationship(String name) throws NullPointerException;

  boolean inherits(ObjectType d);

}
