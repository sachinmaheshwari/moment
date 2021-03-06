package inventory.core.bo;

/*
 * Copyright (c) 2018 Sachin Maheshwari
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

import inventory.core.exceptions.ItemAlreadyAllocatedException;

public class Item {

  private String id;
  private String name;
  private String serialNumber;
  private Boolean isAllocated;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Boolean isAllocated() {
    return isAllocated;
  }

  public void setAllocated(Boolean allocated) {
    isAllocated = allocated;
  }

  public void allocate(User user) {
    if (isAllocated){
      throw new ItemAlreadyAllocatedException("This item is already allocated : " + getId());
    } else {
      setAllocated(Boolean.TRUE);
    }

  }
}
