package inventory.core.usecases.mocks;

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

import inventory.core.Gateway;
import inventory.core.bo.Item;
import inventory.core.bo.User;
import inventory.core.exceptions.ItemNotFoundException;
import inventory.core.exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockGateway implements Gateway {

  private List<Item> itemList = new ArrayList<>();
  private List<User> userList = new ArrayList<>();

  @Override
  public Item save(Item newItem) {
    newItem.setId(UUID.randomUUID().toString());
    itemList.add(newItem);
    return newItem;
  }

  @Override
  public User save(User user) {
    user.setId(UUID.randomUUID().toString());
    userList.add(user);
    return user;
  }

  @Override
  public Item findItemById(String itemId) {
    return itemList.parallelStream().filter(i -> i.getId().equals(itemId)).findAny().orElseThrow(() -> new ItemNotFoundException(itemId));
  }

  @Override
  public User findUserById(String userId) {
    return userList.parallelStream().filter(user -> user.getId().equals(userId)).findAny().orElseThrow(() -> new UserNotFoundException(userId));
  }
}
