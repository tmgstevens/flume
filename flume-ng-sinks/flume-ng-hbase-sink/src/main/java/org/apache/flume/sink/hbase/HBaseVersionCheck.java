/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.flume.sink.hbase;

import org.apache.hadoop.hbase.util.VersionInfo;
import org.slf4j.Logger;

class HBaseVersionCheck {

  private static int getMajorVersion(String version) throws NumberFormatException {
    return Integer.parseInt(version.split("\\.")[0]);
  }

  static boolean hasVersionLessThan2(Logger logger) {
    String version = VersionInfo.getVersion();
    try {
      if (getMajorVersion(version) < 2) {
        return true;
      }
    } catch (NumberFormatException ex) {
      logger.error(ex.getMessage());
    }
    logger.error("Invalid HBase version:" + version);
    return false;
  }
}