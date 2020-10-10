package com.gzy.leeboo.mapper;

import com.gzy.leeboo.dto.AuthenticateHr;
import com.gzy.leeboo.dto.BasicHr;
import com.gzy.leeboo.dto.ChatHr;
import com.gzy.leeboo.dto.ResetPassword;
import com.gzy.leeboo.entity.Hr;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HrMapper {
    Hr getHrByUsername(String username);

    BasicHr getHrById(Integer id);

    String getAvatarById(Integer id);

    List<BasicHr> getAllHrs();

    Integer getHrCountsByRoleId(Integer roleId);

    Integer getHrCountsByRoleIds(List<Integer> roleIds);

    List<ChatHr> getAllHrsWithoutMyself(Integer id);

    Boolean addHr(AuthenticateHr authenticateHr);

    Boolean updateHr(Hr hr);

    Boolean updateHrPassword(ResetPassword resetPassword);

    Boolean updateHrEnabled(BasicHr basicHr);

    Boolean updateHrAvatar(BasicHr basicHr);

    Boolean deleteHrById(Integer id);
}
