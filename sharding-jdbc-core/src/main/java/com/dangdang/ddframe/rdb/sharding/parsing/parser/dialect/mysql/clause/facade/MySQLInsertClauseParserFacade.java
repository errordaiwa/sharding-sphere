package com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.mysql.clause.facade;

import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.parsing.lexer.LexerEngine;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.clause.facade.AbstractInsertClauseParserFacade;
import com.dangdang.ddframe.rdb.sharding.parsing.parser.dialect.mysql.clause.MySQLInsertIntoClauseParser;

/**
 * MySQL的INSERT从句解析器门面类.
 *
 * @author zhangliang
 */
public final class MySQLInsertClauseParserFacade extends AbstractInsertClauseParserFacade {
    
    public MySQLInsertClauseParserFacade(final ShardingRule shardingRule, final LexerEngine lexerEngine) {
        super(new MySQLInsertIntoClauseParser(shardingRule, lexerEngine));
    }
}