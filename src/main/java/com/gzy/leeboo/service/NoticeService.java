package com.gzy.leeboo.service;

import com.gzy.leeboo.entity.Notice;
import com.gzy.leeboo.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {
    private NoticeMapper noticeMapper;

    @Autowired
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public Notice getNotice() {
        return noticeMapper.getNotice();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Boolean updateNotice(Notice notice) {
        return updateNotice(notice);
    }
}
