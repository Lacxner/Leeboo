package com.gzy.leeboo.mapper;

import com.gzy.leeboo.entity.Notice;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {
    Notice getNotice();

    Boolean updateNotice(Notice notice);
}
