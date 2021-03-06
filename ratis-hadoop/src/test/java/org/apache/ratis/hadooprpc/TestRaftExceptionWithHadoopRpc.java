/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ratis.hadooprpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.CommonConfigurationKeys;
import org.apache.ratis.RaftExceptionBaseTest;

public class TestRaftExceptionWithHadoopRpc
    extends RaftExceptionBaseTest<MiniRaftClusterWithHadoopRpc>
    implements MiniRaftClusterWithHadoopRpc.Factory.Get {

  @Override
  public MiniRaftClusterWithHadoopRpc newCluster(int numPeers) {
    final Configuration conf = new Configuration();
    HadoopConfigKeys.Ipc.setHandlers(conf, 20);
    conf.setInt(CommonConfigurationKeys.IPC_CLIENT_CONNECT_MAX_RETRIES_KEY, 0);
    conf.setInt(CommonConfigurationKeys.IPC_SERVER_HANDLER_QUEUE_SIZE_KEY, 1000);
    conf.setInt(CommonConfigurationKeys.IPC_CLIENT_RPC_TIMEOUT_KEY, 1000);
    return MiniRaftClusterWithHadoopRpc.FACTORY.newCluster(numPeers, getProperties(), conf);
  }
}
