package inventory.core.usecases;

import inventory.core.Gateway;
import inventory.core.bo.Item;
import inventory.core.presentation.PresentableItem;

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

public class AddItemUseCase {

  private final Gateway gateway;

  public AddItemUseCase(Gateway gateway) {
    this.gateway = gateway;
  }

  public PresentableItem addItem(PresentableItem newItem) {
    return PresentableItem.buildFrom(gateway.save(getItemFromPresentation(newItem)));
  }

  private Item getItemFromPresentation(PresentableItem newItem) {
    Item item = new Item();
    item.setName(newItem.getName());
    item.setSerialNumber(newItem.getSerialNumber());
    return item;
  }

}
