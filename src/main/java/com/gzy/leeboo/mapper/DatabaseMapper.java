package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Database;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseMapper {
    Database getDatabase();

    Boolean updateDatabase(Database database);
}
