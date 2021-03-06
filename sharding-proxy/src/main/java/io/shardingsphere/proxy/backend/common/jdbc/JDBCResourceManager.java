/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.proxy.backend.common.jdbc;

import io.shardingsphere.core.routing.router.masterslave.MasterVisitedManager;
import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * JDBC resource manager.
 *
 * @author zhaojun
 */
@Getter
public final class JDBCResourceManager {
    
    private final List<Connection> connections = new ArrayList<>();
    
    private final List<Statement> statements = new ArrayList<>();
    
    private final List<ResultSet> resultSets = new CopyOnWriteArrayList<>();
    
    /**
     * Add new connection to resource manager.
     *
     * @param connection Connection
     */
    public void addConnection(final Connection connection) {
        connections.add(connection);
    }
    
    /**
     * Add statement to resource manager.
     *
     * @param statement statement
     */
    public void addStatement(final Statement statement) {
        statements.add(statement);
    }
    
    /**
     * Add new resultSet to resource manager.
     *
     * @param resultSet result set
     */
    public void addResultSet(final ResultSet resultSet) {
        resultSets.add(resultSet);
    }
    
    /**
     * Clear all usable proxy resource in current thread.
     *
     * @throws SQLException SQLException
     */
    public void clear() throws SQLException {
        for (Connection each : connections) {
            if (!each.isClosed()) {
                each.close();
            }
        }
        for (ResultSet each : resultSets) {
            if (!each.isClosed()) {
                each.close();
            }
            MasterVisitedManager.clear();
        }
    }
}
