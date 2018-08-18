package inventory.core.usecases;

import static org.junit.Assert.*;

import inventory.core.Gateway;
import inventory.core.bo.Item;
import inventory.core.bo.User;
import inventory.core.exceptions.ItemAlreadyAllocatedException;
import inventory.core.exceptions.ItemNotFoundException;
import inventory.core.exceptions.UserNotFoundException;
import inventory.core.usecases.mocks.MockGateway;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

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

public class AllocateItemUseCaseTest {

  private Gateway gateway;
  private User user;
  private Item item;
  private AllocateItemUseCase useCase;
  private User userTwo;

  @Before
  public void setUp() throws Exception {
    gateway = new MockGateway();
    user = gateway.save(new User());
    userTwo = gateway.save(new User());
    item = gateway.save(new Item());
    useCase = new AllocateItemUseCase(gateway);
  }

  @Test
  public void allocate() {
    useCase.allocate(item.getId(), user.getId());
  }

  @Test(expected = ItemNotFoundException.class)
  public void testThrowItemNotFoundExceptionForWrongItemId() {
    useCase.allocate("wrongId", user.getId());
  }

  @Test(expected = UserNotFoundException.class)
  public void testThrowUserNotFoundExceptionForWrongUserId() {
    useCase.allocate(item.getId(), "wrong-id");
  }

  @Test
  public void testAfterAllocationUserShouldHaveThatItem() {
    useCase.allocate(item.getId(), user.getId());
    User user = gateway.findUserById(this.user.getId());
    Optional<Item> allocatedItem = user.getAllocatedItems().parallelStream()
        .filter(i -> i.getId().equals(item.getId())).findAny();
    assertTrue(allocatedItem.isPresent());
  }

  @Test(expected = ItemAlreadyAllocatedException.class)
  public void testAlreadyAllocatedItem() {
    useCase.allocate(item.getId(), user.getId());
    useCase.allocate(item.getId(), userTwo.getId());
  }

}