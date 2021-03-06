/*
 * Copyright 2017 Huawei Technologies Co., Ltd
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

package io.servicecomb.serviceregistry.discovery;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.foundation.common.cache.VersionedCache;

public class TestDiscoveryTreeNode {
  DiscoveryTreeNode node = new DiscoveryTreeNode();

  @Test
  public void childrenInited() {
    Assert.assertFalse(node.childrenInited());

    node.childrenInited(true);
    Assert.assertTrue(node.childrenInited());
  }

  @Test
  public void level() {
    node.level(1);

    Assert.assertEquals(1, node.level());
  }

  @Test
  public void attribute() {
    node.attribute("k1", "v1");

    Assert.assertEquals("v1", node.attribute("k1"));
  }

  @Test
  public void children() {
    Map<String, DiscoveryTreeNode> children = new HashMap<>();
    node.children(children);

    Assert.assertSame(children, node.children());
  }

  @Test
  public void child() {
    DiscoveryTreeNode child = new DiscoveryTreeNode().name("child");
    node.child(child.name(), child);

    Assert.assertSame(child, node.child(child.name()));
  }

  @Test
  public void fromCache() {
    Object data = new Object();
    VersionedCache other = new VersionedCache().cacheVersion(1).name("cache").data(data);
    node.fromCache(other);

    Assert.assertEquals(1, node.cacheVersion());
    Assert.assertEquals("cache", node.name());
    Assert.assertSame(data, node.data());
  }
}
