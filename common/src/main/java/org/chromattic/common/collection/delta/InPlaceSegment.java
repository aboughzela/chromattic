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
package org.chromattic.common.collection.delta;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a>
 * @version $Revision$
 */
class InPlaceSegment<E> extends Segment<E> {

  /** . */
  final DeltaList<E, ?> owner;

  /** . */
  private Segment<E> previous;

  /** . */
  private Segment<E> next;

  /** The index in the list. */
  int listIndex;

  /** The size in the list. */
  int listSize;

  public InPlaceSegment(DeltaList<E, ?> owner) {
    this.owner = owner;
  }

  @Override
  Segment<E> getNext() {
    return next;
  }

  @Override
  void setNext(Segment<E> next) {
    this.next = next;
  }

  @Override
  Segment<E> getPrevious() {
    return previous;
  }

  @Override
  void setPrevious(Segment<E> previous) {
    this.previous = previous;
  }

  @Override
  protected E localGet(int index) {
    return owner.listget(listIndex + index);
  }

  @Override
  protected int localSize() {
    return listSize;
  }

  @Override
  protected boolean localCanAdd(int index) {
    return index < listSize;
  }

  @Override
  protected void localAdd(int index, E element) {
    InsertionSegment<E> is = new InsertionSegment<E>();
    is.insertions.add(element);

    //
    InPlaceSegment<E> ips = new InPlaceSegment<E>(owner);
    ips.listIndex = listIndex + index;
    ips.listSize = listSize - index;

    //
    listSize = index;

    //
    addAfter(is).addAfter(ips);
  }

  @Override
  protected E localRemove(int index) {
    if (index  == 0) {
      E removed = owner.listget(listIndex);
      listIndex++;
      listSize--;
      return removed;
    } else if (index == listSize - 1) {
      return owner.listget(listIndex + --listSize);
    } else {
      InPlaceSegment<E> ips = new InPlaceSegment<E>(owner);

      //
      ips.listIndex = listIndex + index + 1;
      ips.listSize = listSize - index - 1;

      //
      listSize = index;

      //
      addAfter(ips);

      //
      return owner.listget(index);
    }
  }

  @Override
  public Iterator<E> localIterator() {
    return new Iterator<E>() {
      int index = 0;
      public boolean hasNext() {
        return index < listSize;
      }
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return owner.listget(listIndex + index++);
      }
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  protected void format(StringBuilder builder) {
    builder.append("(");
    int count = 0;
    for (int i = listIndex;i < listIndex + listSize;i++) {
      E e = owner.listget(i);
      if (count > 0) {
        builder.append(",");
      }
      builder.append(e);
      count++;
    }
    builder.append(")");
  }
}
