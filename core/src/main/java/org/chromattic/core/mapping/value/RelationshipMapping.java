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

package org.chromattic.core.mapping.value;

import org.chromattic.api.RelationshipType;
import org.reflext.api.ClassTypeInfo;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
public abstract class RelationshipMapping extends ValueMapping {

  /** . */
  private ClassTypeInfo relatedType;

  /** . */
  private final Multiplicity multiplicity;

  /** . */
  private final Multiplicity relatedMultiplicity;

  /** . */
  private final RelationshipType type;

  public RelationshipMapping(ClassTypeInfo relatedType, Multiplicity multiplicity, Multiplicity relatedMultiplicity, RelationshipType type) {
    this.relatedType = relatedType;
    this.multiplicity = multiplicity;
    this.relatedMultiplicity = relatedMultiplicity;
    this.type = type;
  }

  public RelationshipType getType() {
    return type;
  }

  public ClassTypeInfo getRelatedType() {
    return relatedType;
  }

  public Multiplicity getMultiplicity() {
    return multiplicity;
  }

  public Multiplicity getRelatedMultiplicity() {
    return relatedMultiplicity;
  }
}