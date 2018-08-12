package inventory.core.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import inventory.core.Gateway;
import inventory.core.presentation.PresentableItem;
import inventory.core.usecases.mocks.MockGateway;
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

public class AddItemUseCaseTest {

  private AddItemUseCase useCase;
  private Gateway gateway = new MockGateway();

  @Before
  public void setUp() {
    useCase = new AddItemUseCase(gateway);
  }

  @Test
  public void addItem() {
    PresentableItem presentableItem = useCase.addItem(new PresentableItem());
    assertNotNull(presentableItem);
    assertNotNull(presentableItem.getId());
  }

  @Test
  public void testAddMultipleItems() {
    PresentableItem presentableItem1 = useCase.addItem(new PresentableItem());
    PresentableItem presentableItem2 = useCase.addItem(new PresentableItem());
    assertNotNull(presentableItem1);
    assertNotNull(presentableItem1.getId());
    assertNotNull(presentableItem2);
    assertNotNull(presentableItem2.getId());
    assertNotEquals(presentableItem1.getId(), presentableItem2.getId());
  }

  @Test
  public void testDataIsCorrectlySaved() {
    PresentableItem p = new PresentableItem();
    p.setName("p-item-name");
    p.setSerialNumber("p-serial-number");
    PresentableItem savedItem = useCase.addItem(p);
    assertNotNull(savedItem);
    assertEquals(p.getName(), savedItem.getName());
    assertEquals(p.getSerialNumber(), savedItem.getSerialNumber());
  }
}